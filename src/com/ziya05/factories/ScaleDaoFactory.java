package com.ziya05.factories;

import com.ziya05.dao.IScaleDao;
import com.ziya05.dao.FakeScaleDao;

public class ScaleDaoFactory {
	public static IScaleDao createScaleDao() {
		return new FakeScaleDao();
	}
}
