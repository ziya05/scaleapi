package com.ziya05.dao;

import java.sql.SQLException;
import java.util.List;

import com.ziya05.entities.Factor;
import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Scale;

public interface IScaleDao {
	List<Scale> getAllScales() throws ClassNotFoundException, SQLException;
	
	PersonalInfo getPersonalInfoByScaleId(int id) throws ClassNotFoundException, SQLException;
	
	Scale getScaleByScaleId(int id) throws ClassNotFoundException, SQLException;
	
	List<Factor> getFactorListByScale(int id) throws ClassNotFoundException, SQLException;
}
