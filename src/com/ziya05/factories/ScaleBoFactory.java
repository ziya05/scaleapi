package com.ziya05.factories;

import javax.naming.NamingException;

import com.ziya05.bo.IScaleBo;
import com.ziya05.bo.ScaleBo;

public class ScaleBoFactory {
	public static IScaleBo createScaleBo() throws NamingException {
		return new ScaleBo();
	}
}
