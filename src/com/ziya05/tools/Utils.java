package com.ziya05.tools;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public final class Utils {
	
	public static Object evel(String formula, Map<String, Object> map) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		
		for(String key : map.keySet()) {
			engine.put(key, map.get(key));
		}

		Object result = engine.eval(formula);
		return result;
	}
	
}
