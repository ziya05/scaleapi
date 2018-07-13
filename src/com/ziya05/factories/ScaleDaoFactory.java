package com.ziya05.factories;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ziya05.dao.IScaleDao;
import com.ziya05.dao.ScaleDao;

public class ScaleDaoFactory {
	public static IScaleDao createScaleDao() throws NamingException {
		Context sourceCtx = new InitialContext(); 
		DataSource ds = 
				(DataSource)sourceCtx.lookup("java:comp/env/jdbc/scale");
		return new ScaleDao(ds);
	}
}
