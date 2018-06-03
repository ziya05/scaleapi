package com.ziya05.dao;

import java.sql.SQLException;
import java.util.List;

import com.ziya05.entities.Factor;
import com.ziya05.entities.Group;
import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Relation;
import com.ziya05.entities.Scale;

public interface IScaleDao {
	List<Scale> getAllScales() throws ClassNotFoundException, SQLException;
	
	PersonalInfo getPersonalInfoByScaleId(int scaleId) throws ClassNotFoundException, SQLException;
	
	Scale getScaleByScaleId(int scaleId) throws ClassNotFoundException, SQLException;
	
	List<Factor> getFactorListByScale(int scaleId) throws ClassNotFoundException, SQLException;
	
	List<Group> getGroupListByScale(int scaleId);
	
	List<Relation> getRelationByScale(int scaleId);
}
