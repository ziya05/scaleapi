package com.ziya05.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ziya05.entities.UserHistoryData;
import com.ziya05.factories.ScaleDaoFactory;
import com.ziya05.tools.Utils;

public class ScaleBo implements IScaleBo {
	private IScaleDao dao = ScaleDaoFactory.createScaleDao();

	@Override
	public Result getResult(int scaleId, UserHistoryData data) throws ClassNotFoundException, SQLException, ScriptException {
		List<Factor> factorLst = dao.getFactorListByScale(scaleId);
		List<Group> groupLst = dao.getGroupListByScale(scaleId);
		List<Relation> relationLst = dao.getRelationByScale(scaleId);
		
		List<OptionSelected> osLst = data.getData().getItems();
		Map<String, Object> map = new HashMap<String, Object>();
		for(OptionSelected os : osLst) {

			map.put("Q"+os.getQuestionId(), (Object)os.getScore());
		}
		
		List<FactorScore> factorScoreLst = new ArrayList<FactorScore>();
		for(Factor factor : factorLst) {
			double score = this.calcScore(factor.getFormula(), map);
			map.put("F" + factor.getFactorId(), score);
			
			FactorScore factorScore = new FactorScore();
			factorScore.setFactorId(factor.getFactorId());
			factorScore.setScore(score);
			factorScoreLst.add(factorScore);
			
			System.out.println("factorscore---------" + factor.getFactorId() + " : " + score);
		}
		
		map = new HashMap<String, Object>();
		map.put("ÐÔ±ð", data.getInfo().getGender());
		for(InfoItem item : data.getInfo().getItems()) {
			map.put(item.getTitle(), item.getContent());
		}
		
		
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
		
		
		Result result = new Result();
		List<FactorResult> factorResultLst = new ArrayList<FactorResult>();
		result.setItems(factorResultLst);
		result.setGroupLst(new ArrayList<String>());
		for(Group group : groupLst) {
			result.getGroupLst().add(group.getName());
		}
		
		for(Relation relation : relationLst) {
			if(groupIdLst.contains(relation.getGroundId())) {
				int levelId = this.getLevelId(relation.getFactorId(), relation.getPoints(), factorScoreLst);
				
				for(Factor factor : factorLst) {
					if(factor.getFactorId() == relation.getFactorId()) {
						FactorResult factorResult = this.getFactorResult(levelId, factor);
						factorResultLst.add(factorResult);
						break;
					}
				}
				
			}
		}

		return result;
	}
	
	private FactorResult getFactorResult(int levelId, Factor factor) {
		FactorResult factorResult = new FactorResult();
		for(Level level : factor.getLevelList()) {
			if (levelId == level.getLevelId()) {
				factorResult.setName(factor.getName());
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
					if(pointLst.get(i) > fs.getScore()) {
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
		return new Double(Utils.evel(formula, map).toString());
	}
}
