package com.ziya05.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.ziya05.dao.IScaleDao;
import com.ziya05.entities.Factor;
import com.ziya05.entities.FactorMap;
import com.ziya05.entities.FactorResult;
import com.ziya05.entities.FactorScore;
import com.ziya05.entities.GlobalJump;
import com.ziya05.entities.Group;
import com.ziya05.entities.InfoItem;
import com.ziya05.entities.Option;
import com.ziya05.entities.OptionSelected;
import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Question;
import com.ziya05.entities.QuestionType;
import com.ziya05.entities.Relation;
import com.ziya05.entities.Result;
import com.ziya05.entities.Scale;
import com.ziya05.entities.ScaleException;
import com.ziya05.entities.TesteeData;
import com.ziya05.factories.ScaleDaoFactory;
import com.ziya05.tools.Projects;
import com.ziya05.tools.Utils;

public class ScaleBo implements IScaleBo {
	
	private final String QUESTION_JUMP = "-1";
	
	private IScaleDao dao = null;
	
	public ScaleBo() throws NamingException {
		this.dao = ScaleDaoFactory.createScaleDao();
	}
	
	public List<Scale> getScales() throws SQLException, ClassNotFoundException{
		return dao.getAllScales();
	}
	
	public PersonalInfo getPersonalInfoById(int scaleId) throws SQLException, ClassNotFoundException{
		return dao.getPersonalInfoByScaleId(scaleId);
	}
	
	@Override
	public Scale getScaleById(int scaleId) throws SQLException, ClassNotFoundException {

		Scale scale = dao.getScaleByScaleId(scaleId);
		
		List<GlobalJump> jumpItems = dao.getGlobalJumpByScale(scaleId);
		scale.setJumpItems(jumpItems);
		
		return scale;
	}

	@Override
	public Result getResult(int scaleId, TesteeData data) throws ClassNotFoundException, SQLException, ScriptException {
		Scale scale = dao.getScaleByScaleId(scaleId);

		List<Factor> factorLst = dao.getFactorListByScale(scaleId);
		
		List<Relation> relationLst = dao.getRelationByScale(scaleId);
		
		//初始化因子结果相关对象
		Result result = new Result();
		result.setData(data);
		
		List<Integer> groupIdLst = this.getUserGroup(scaleId, data, result);
		
		List<FactorResult> factorResultLst = new ArrayList<FactorResult>();
		result.setItems(factorResultLst);	
		
		//计算因子分
		ScriptEngine factorEngine = this.getFactorEngine(scaleId, scale, data);
		List<FactorScore> factorScoreLst = new ArrayList<FactorScore>();
		for(Factor factor : factorLst) {

			double score = (double)factorEngine.eval(convertFormula(factor.getFormula()));
			factorEngine.put("F" + factor.getFactorId(), score);
			
			FactorScore factorScore = new FactorScore();
			factorScore.setFactorId(factor.getFactorId());
			factorScore.setScore(score);
			factorScoreLst.add(factorScore);
			
			FactorResult factorResult = new FactorResult();
			int levelId = 0;
			
			if (factor.getLevelCount() > 0) {
				//确定等级、解释及建议信息
				for(Relation relation : relationLst) {
					if(groupIdLst.contains(relation.getGroupId())
							&& factor.getFactorId() == relation.getFactorId()) {
						levelId = this.getLevelId(relation.getFactorId(), relation.getPoints(), factorScoreLst);

						break; 
					}
				}
			} 

			factorResult.setFactorId(factor.getFactorId());
			factorResult.setName(factor.getName());
			factorResult.setLevelId(levelId);
			factorResult.setScore(score);
			factorResultLst.add(factorResult);

			factorEngine.put("LEVEL_F" + factor.getFactorId(), factorResult.getLevelId());
			
		}
		
		return result;
	}
	
	private List<Integer> getUserGroup(int scaleId, TesteeData data, Result result) throws ClassNotFoundException, SQLException, ScriptException {
		List<Group> groupLst = dao.getGroupListByScale(scaleId);
		
		//确定所属团体
		Map<String, Object> map = this.loadPersonalInfo(null, data);
		//为了减少内存开销， 将复用ScriptEngine
		ScriptEngine groupEngine = Utils.createEngine(map, null);
		
		
		List<Integer> groupIdLst = new ArrayList<Integer>();
		int groupLen = groupLst.size();
		for(int i = groupLen-1; i >= 0; i--) {
			Group group = groupLst.get(i);
			Boolean isGroup = (Boolean)groupEngine.eval(group.getFormula());
			
			if (!isGroup) {
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
			throw new ScaleException("该用户不属于任何量表支持团体！");
		}	
		
		return groupIdLst;
	}
	
	private ScriptEngine getFactorEngine(int scaleId, Scale scale, TesteeData data) throws ClassNotFoundException, SQLException, ScriptException {
		
		Map<Integer, Integer> qtMap = this.getQuestionTypeMap(scale);
		Map<String, Integer> osMap = this.getOptionScoreMap(scale);
		
		List<FactorMap> factorMapLst = dao.getFactorMapByScale(scaleId);
		
		List<OptionSelected> osLst = data.getData().getItems();
		Map<String, Object> map = this.loadPersonalInfo(null, data);
		for(OptionSelected os : osLst) {
			
			String questionType = qtMap.get(os.getQuestionId()).toString();
			
			int score = 0;
			
			if (os.getOptionId().equals(QUESTION_JUMP) 
					|| questionType.equals(QuestionType.DESCRIPTION.toString())) {
				// 什么都不做
			} else if (questionType.equals(QuestionType.SINGLE.toString())) {
				String key = String.format("%s|%s", os.getQuestionId(), os.getOptionId());
				score = osMap.get(key);
			} else if (questionType.equals(QuestionType.MULTPLE.toString())) { 
				
				char[] chs = os.getOptionId().toCharArray();
				for(char ch : chs) {
					String key = String.format("%s|%s", os.getQuestionId(), ch);
					score += osMap.get(key);
				}
			}
			
			os.setScore(score);
			map.put("P" + os.getQuestionId(), os.getOptionId());
			map.put("Q"+os.getQuestionId(), score);
		}
		
		List<String> functionLst = Projects.getFactorFunctionLst();
		List<String> currFunctionLst = new ArrayList<String>();
		currFunctionLst.addAll(functionLst);
		if (factorMapLst != null) {
			for (FactorMap factorMap : factorMapLst) {
				String factorMapfunc = String.format("function Map_FM%d_%s(X){ %s }", 
						factorMap.getFactorId(),
						factorMap.getName(),
						factorMap.getFormula());
				
				currFunctionLst.add(factorMapfunc);
			}
		}
				
		//为了减少内存开销， 将复用ScriptEngine
		ScriptEngine engineFactor = Utils.createEngine(map, currFunctionLst);
		
		return engineFactor;
	}
	
	private Map<String, Integer> getOptionScoreMap(Scale scale) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(Question question : scale.getItems()) {
			for(Option option : question.getItems()) {
				map.put(question.getId() + "|" + option.getOptionId(), option.getScore());
			}
		}
		
		return map;
	}
	
	private Map<Integer, Integer> getQuestionTypeMap(Scale scale) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(Question question : scale.getItems()) {

			map.put(question.getId(), question.getQuestionType());
		}
		
		return map;
	}
		
	private int getLevelId(int factorId, String points, List<FactorScore> factorScoreLst) {
		int level = 0;
		for(FactorScore fs : factorScoreLst) {
			if (factorId == fs.getFactorId()) {

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
		List<Double> pointLst = new ArrayList<Double>();
		for(String elem : pointArr) {
			pointLst.add(new Double(elem.trim()));
		}
		Collections.sort(pointLst);
		
		return pointLst;
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
		
		if (range.length == 2) {
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
		} else if (range.length == 1) {
			sb.append(data);
		} else {
			throw new UnsupportedOperationException("目前暂不支持该格式！" + m.group(0));
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
		dao.insertTesteePersonalInfo(scaleId, baseId, data);
		dao.insertTesteeData(scaleId, baseId, data);
		
		return baseId;
	}
	
	@Override
	public void saveResult(int scaleId, int baseId, Result result) throws ClassNotFoundException, SQLException {
		
		StringBuilder sb = new StringBuilder();
		List<OptionSelected> osLst = result.getData().getData().getItems();
		for(OptionSelected os : osLst) {
			sb.append(os.getScore() + ",");
		}
		dao.updateResultScore(scaleId, baseId, sb.toString());
		
		if (result.getGroupLst() != null 
				&& result.getGroupLst().size() > 0) {
			dao.insertResultBase(scaleId, baseId, result);
		}
		
		if (result.getItems() != null
				&& result.getItems().size() > 0) {
			dao.insertResultFactor(scaleId, baseId, result);
		}
	}

}
