package com.ziya05.bo;

import java.sql.SQLException;

import javax.script.ScriptException;

import com.ziya05.entities.Result;
import com.ziya05.entities.SelectedData;

public interface IScaleBo {
	Result getResult(int id, SelectedData selected) throws ClassNotFoundException, SQLException, ScriptException;
}
