package com.ziya05.bo;

import java.sql.SQLException;

import javax.script.ScriptException;

import com.ziya05.entities.Result;
import com.ziya05.entities.SelectedData;
import com.ziya05.entities.UserHistoryData;

public interface IScaleBo {
	Result getResult(int scaleId, UserHistoryData data) throws ClassNotFoundException, SQLException, ScriptException;
}
