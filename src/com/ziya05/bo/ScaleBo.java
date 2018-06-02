package com.ziya05.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.ziya05.dao.IScaleDao;
import com.ziya05.entities.Factor;
import com.ziya05.entities.FactorPair;
import com.ziya05.entities.Level;
import com.ziya05.entities.OptionSelected;
import com.ziya05.entities.Result;
import com.ziya05.entities.SelectedData;
import com.ziya05.factories.ScaleDaoFactory;

public class ScaleBo implements IScaleBo {
	private IScaleDao dao = ScaleDaoFactory.createScaleDao();

	@Override
	public Result getResult(int id, SelectedData selected) throws ClassNotFoundException, SQLException, ScriptException {
		List<Factor> factorLst = dao.getFactorListByScale(id);
		List<OptionSelected> osLst = selected.getItems();
		
		
		Result result = new Result();
		List<FactorPair> pairLst = new ArrayList<FactorPair>();
		result.setItems(pairLst);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(OptionSelected os : osLst) {

			map.put("Q"+os.getQuestionId(), os.getScore());
		}
		
		for(Factor factor : factorLst) {
			double score = this.calcScore(factor.getFormula(), map);

			FactorPair pair = null;
			for(Level level : factor.getLevelList()) {
				if (level.getMinValue() <= score 
						&& level.getMaxValue() >= score) {
					pair = new FactorPair(level.getName(), level.getDescription());
					break;
				}
			}
			pairLst.add(pair);
		
		}

		return result;
	}

	private double calcScore(String formula, Map<String, Integer> map) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		for(String key : map.keySet()) {
			engine.put(key, map.get(key));
		}

		Object result = engine.eval(formula);

		double d = new Double(result.toString());
		return d;
	}
}
