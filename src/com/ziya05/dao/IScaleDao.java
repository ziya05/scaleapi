package com.ziya05.dao;

import java.sql.SQLException;
import java.util.List;

import com.ziya05.entities.Factor;
import com.ziya05.entities.Group;
import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Relation;
import com.ziya05.entities.Result;
import com.ziya05.entities.Scale;
import com.ziya05.entities.TesteeData;

public interface IScaleDao {
	List<Scale> getAllScales() throws ClassNotFoundException, SQLException;
	
	PersonalInfo getPersonalInfoByScaleId(int scaleId) throws ClassNotFoundException, SQLException;
	
	Scale getScaleByScaleId(int scaleId) throws ClassNotFoundException, SQLException;
	
	List<Factor> getFactorListByScale(int scaleId) throws ClassNotFoundException, SQLException;
	
	List<Group> getGroupListByScale(int scaleId) throws ClassNotFoundException, SQLException;
	
	List<Relation> getRelationByScale(int scaleId) throws ClassNotFoundException, SQLException;
	
	int insertTesteeBase(int scaleId, TesteeData data) throws ClassNotFoundException, SQLException;
	
	void insertTesteePersonalInfo(int scaleId, int baseId, TesteeData data) throws ClassNotFoundException, SQLException;
	
	void insertTesteeData(int scaleId, int baseId, TesteeData data) throws ClassNotFoundException, SQLException;
	
	void insertResultBase(int scaleId, int testeeBaseId, Result result) throws ClassNotFoundException, SQLException;
	
	void insertResultFactor(int scaleId, int testeeBaseId, Result result) throws ClassNotFoundException, SQLException;
}
