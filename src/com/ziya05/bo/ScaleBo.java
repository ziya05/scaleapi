package com.ziya05.bo;

import java.util.ArrayList;
import java.util.List;

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
	public Result getResult(int id, SelectedData selected) {
		List<Factor> factorLst = dao.getFactorListByScale(id);
		List<OptionSelected> osLst = selected.getItems();
		
		
		Result result = new Result();
		List<FactorPair> pairLst = new ArrayList<FactorPair>();
		result.setItems(pairLst);
		
		for(Factor factor : factorLst) {
			int sum = 0;
			for(Integer i : factor.getItems()) {
				for(OptionSelected os : osLst) {
					if (i.intValue() == os.getQuestionId()) {
						sum += os.getScore();
						break;
					}
				}
			}
			
			FactorPair pair = null;
			for(Level level : factor.getLevelList()) {
				if (level.getMinValue() <= sum 
						&& level.getMaxValue() >= sum) {
					pair = new FactorPair(level.getName(), level.getDescription());
					break;
				}
			}
			pairLst.add(pair);
		
		}

		return result;
	}

}
