package com.ziya05.tools;

import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public final class Utils {
	
	public static ScriptEngine createEngine() throws ScriptException {
		return Utils.createEngine(null, null);
	}
	
	public static ScriptEngine createEngine(Map<String, Object> map, 
			List<String> functionLst) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		
		if(map != null) {
			for(String key : map.keySet()) {
				engine.put(key, map.get(key));
			}
		}
		
		if (functionLst != null) {
			for (String function : functionLst) {
				engine.eval(function);
			}
		}

		return engine;
	}
	
	public static Object evel(String formula, 
			Map<String, Object> map, 
			List<String> functionLst) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		
		for(String key : map.keySet()) {
			engine.put(key, map.get(key));
		}
		
		for (String function : functionLst) {
			engine.eval(function);
		}

		Object result = engine.eval(formula);
		
		return result;
	}
	
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
