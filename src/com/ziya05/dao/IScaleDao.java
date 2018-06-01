package com.ziya05.dao;

import java.util.List;

import com.ziya05.entities.Factor;
import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Scale;

public interface IScaleDao {
	List<Scale> getAllScales();
	
	PersonalInfo getPersonalInfoByScaleId(int id);
	
	Scale getScaleByScaleId(int id);
	
	List<Factor> getFactorListByScale(int id);
}
