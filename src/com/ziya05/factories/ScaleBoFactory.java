package com.ziya05.factories;

import com.ziya05.bo.IScaleBo;
import com.ziya05.bo.ScaleBo;

public class ScaleBoFactory {
	public static IScaleBo createScaleBo(int id) {
		return new ScaleBo();
	}
}
