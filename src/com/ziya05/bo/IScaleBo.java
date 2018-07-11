package com.ziya05.bo;

import java.sql.SQLException;
import java.util.List;

import javax.script.ScriptException;

import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Result;
import com.ziya05.entities.Scale;
import com.ziya05.entities.TesteeData;

public interface IScaleBo {
	
	List<Scale> getScales() throws SQLException, ClassNotFoundException;
	
	PersonalInfo getPersonalInfoById(int scaleId) throws SQLException, ClassNotFoundException;
	
	Scale getScaleById(int scaleId) throws SQLException, ClassNotFoundException;
	
	Result getResult(int scaleId, TesteeData data) throws ClassNotFoundException, SQLException, ScriptException;

	int saveTesteeData(int scaleId, TesteeData data) throws ClassNotFoundException, SQLException;
	
	void saveResult(int scaleId, int baseId, Result result) throws ClassNotFoundException, SQLException;
}
