package com.ziya05.bo;

import java.sql.SQLException;

import javax.script.ScriptException;

import com.ziya05.entities.Result;
import com.ziya05.entities.SelectedData;
import com.ziya05.entities.TesteeData;

public interface IScaleBo {
	Result getResult(int scaleId, TesteeData data) throws ClassNotFoundException, SQLException, ScriptException;

	int saveTesteeData(int scaleId, TesteeData data) throws ClassNotFoundException, SQLException;
	
	void saveResult(int scaleId, int baseId, Result result) throws ClassNotFoundException, SQLException;
}
