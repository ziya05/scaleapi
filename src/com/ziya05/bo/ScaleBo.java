package com.ziya05.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.ziya05.dao.IScaleDao;
import com.ziya05.entities.Factor;
import com.ziya05.entities.FactorResult;
import com.ziya05.entities.FactorScore;
import com.ziya05.entities.Group;
import com.ziya05.entities.InfoItem;
import com.ziya05.entities.Level;
import com.ziya05.entities.OptionSelected;
import com.ziya05.entities.Relation;
import com.ziya05.entities.Result;
import com.ziya05.entities.SelectedData;
import com.ziya05.entities.TesteeData;
import com.ziya05.factories.ScaleDaoFactory;
import com.ziya05.tools.Projects;
import com.ziya05.tools.Utils;

public class ScaleBo implements IScaleBo {
	private IScaleDao dao = null;
	
	public ScaleBo() throws NamingException {
		this.dao = ScaleDaoFactory.createScaleDao();
	}

	@Override
	public Result getResult(int scaleId, TesteeData data) throws ClassNotFoundException, SQLException, ScriptException {
		List<Factor> factorLst = dao.getFactorListByScale(scaleId);
		List<Group> groupLst = dao.getGroupListByScale(scaleId);
		List<Relation> relationLst = dao.getRelationByScale(scaleId);
		
		//初始化因子结果相关对象
		Result result = new Result();
		
		//确定所属团体
		Map<String, Object> map = this.loadPersonalInfo(null, data);
		List<Integer> groupIdLst = new ArrayList<Integer>();
		int groupLen = groupLst.size();
		for(int i = groupLen-1; i >= 0; i--) {
			Group group = groupLst.get(i);
			if(!isGroup(group.getFormula(), map)) {
				groupLst.remove(i);
			} else {
				groupIdLst.add(group.getGroupId());
			}
		}
		
		Collections.sort(groupIdLst);	
		
		result.setGroupLst(new ArrayList<String>());
		for(Group group : groupLst) {
			result.getGroupLst().add(group.getName());
		}
		
		if (groupLst.size() == 0) {
			throw new UnsupportedOperationException("该用户不属于任何量表支持团体！");
		}
		
		List<FactorResult> factorResultLst = new ArrayList<FactorResult>();
		result.setItems(factorResultLst);		
		
		//计算因子分
		List<OptionSelected> osLst = data.getData().getItems();
		map = this.loadPersonalInfo(null, data);
		for(OptionSelected os : osLst) {

			map.put("Q"+os.getQuestionId(), (Object)os.getScore());
		}
		
		List<FactorScore> factorScoreLst = new ArrayList<FactorScore>();
		for(Factor factor : factorLst) {
			System.out.println("will calc factor----->" + factor.getFactorId());
			
			double score = this.calcScore(factor.getFormula(), map);
			map.put("F" + factor.getFactorId(), score);
			
			FactorScore factorScore = new FactorScore();
			factorScore.setFactorId(factor.getFactorId());
			factorScore.setScore(score);
			factorScoreLst.add(factorScore);
			
			System.out.println("factorscore--------->" + factor.getFactorId() + " : " + score);
			
			//确定等级、解释及建议信息
			for(Relation relation : relationLst) {
				if(groupIdLst.contains(relation.getGroupId())
						&& factor.getFactorId() == relation.getFactorId()) {
					int levelId = this.getLevelId(relation.getFactorId(), relation.getPoints(), factorScoreLst);
					FactorResult factorResult = this.getFactorResult(levelId, factor);
					if (factorResult != null) {
						factorResult.setScore(score);
						factorResultLst.add(factorResult);
					}
					
					map.put("LEVEL_F" + factor.getFactorId(), levelId);
					System.out.println("set LEVEL_F" + factor.getFactorId());
					
					break; 
				}
			}
		}

		return result;
	}
	
	private FactorResult getFactorResult(int levelId, Factor factor) {
		FactorResult factorResult = null;
		for(Level level : factor.getLevelList()) {
			if (levelId == level.getLevelId()) {
				factorResult = new FactorResult();
				factorResult.setFactorId(factor.getFactorId());
				factorResult.setName(factor.getName());
				factorResult.setLevelId(levelId);
				factorResult.setDescription(level.getDescription());
				factorResult.setAdvice(level.getAdvice());
				break;
			}
		}
		
		return factorResult;
	}
	
	private int getLevelId(int factorId, String points, List<FactorScore> factorScoreLst) {
		int level = 0;
		for(FactorScore fs : factorScoreLst) {
			if (factorId == fs.getFactorId()) {
				double score = fs.getScore();
				List<Double> pointLst = this.getPointLst(points);
				 
				for(int i=0; i < pointLst.size(); i++) {
					if(pointLst.get(i) >= fs.getScore()) { //比如5,6 -> 无穷小, 5] (5, 6] (6, 无穷大
						level = i + 1;
						break;
					}
				}
				
				if (level == 0) {
					level = pointLst.size() + 1;
				}
				
				break;
			}
		}
		
		return level;
	}
	
	private List<Double> getPointLst(String points) {
		String[] pointArr = points.split(",");
		List<Double> pointLst = new ArrayList();
		for(String elem : pointArr) {
			pointLst.add(new Double(elem.trim()));
		}
		Collections.sort(pointLst);
		
		return pointLst;
	}
	
	private Boolean isGroup(String formula, Map<String, Object> map) throws ScriptException {
		return (Boolean)Utils.evel(formula, map);
	}

	private double calcScore(String formula, Map<String, Object> map) throws ScriptException {
		return new Double(Utils.evel(convertFormula(formula), map, Projects.getFactorFunctionLst()).toString());
	}
	
	private String convertFormula(String formula) {
		StringBuffer result = new StringBuffer();
		Pattern pattern = Pattern.compile("isMax\\((.*?)\\|(.*?)\\)");
		
		Matcher matcher = pattern.matcher(formula);
		while(matcher.find()) {
			matcher.appendReplacement(result, convertIsMax(matcher));
		}
		
		matcher.appendTail(result);
		
		return result.toString();
	}
	
	private String convertIsMax(Matcher m) {
		String data = m.group(1);
		String[] range = data.split(":");
		
		StringBuilder sb = new StringBuilder();
		sb.append("isMax([");
		
		Pattern pattern = Pattern.compile("([a-zA-Z]*)([0-9]*)");
		
		Matcher m1 = pattern.matcher(range[0]);
		m1.find();
		
		String prefix = m1.group(1);
		int begin = new Integer(m1.group(2));
		
		Matcher m2 = pattern.matcher(range[1]);
		m2.find();
		int end = new Integer(m2.group(2));
		
		for(int i = begin; i <= end; i++) {
			sb.append(prefix + i);
			if (i != end) {
				sb.append(",");
			}
		}
		
		sb.append("],");
		sb.append("[" + m.group(2) + "])");
		
		return sb.toString();
	}

	private Map<String, Object> loadPersonalInfo(HashMap<String, Object> map,
			TesteeData data) {
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		
		map.put("性别", data.getInfo().getGender());
		map.put("年龄", data.getInfo().getAge());
		
		List<InfoItem> items = data.getInfo().getItems();
		for (InfoItem item : items) {
			map.put(item.getTitle(), item.getContent());
		}
		
		return map;
	}
	
	@Override
	public int saveTesteeData(int scaleId, TesteeData data) throws ClassNotFoundException, SQLException {
		int baseId = dao.insertTesteeBase(scaleId, data);
		System.out.println(baseId);
		dao.insertTesteePersonalInfo(scaleId, baseId, data);
		dao.insertTesteeData(scaleId, baseId, data);
		
		return baseId;
	}
	
	@Override
	public void saveResult(int scaleId, int baseId, Result result) throws ClassNotFoundException, SQLException {
		dao.insertResultBase(scaleId, baseId, result);
		dao.insertResultFactor(scaleId, baseId, result);
	}

}
