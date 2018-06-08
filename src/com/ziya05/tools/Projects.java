package com.ziya05.tools;

import java.util.ArrayList;
import java.util.List;

public class Projects {
	private static List<String> factorFunctionLst = new ArrayList<String>() {{
		add("function ifElse(c, t, f) { if (c==true) return t; else return f; }");
		add("function sortNumber(a, b) {\r\n" + 
				"				return a - b;\r\n" + 
				"			}");
		add("function isMax(arr1, arr2) {\r\n" + 
				"				var arr = arr1.sort(sortNumber).reverse();\r\n" + 
				"				if (arr2.length > arr1.length) {\r\n" + 
				"					return false;\r\n" + 
				"				}\r\n" + 
				"                \r\n" + 
				"				var isSame = true;\r\n" + 
				"				for(var i = 0; i < arr2.length; i++) {\r\n" + 
				"					if (arr2[i] != arr1[i]) {\r\n" + 
				"						isSame = false;\r\n" + 
				"						break;\r\n" + 
				"					}\r\n" + 
				"				}\r\n" + 
				"                \r\n" + 
				"				return isSame;\r\n" + 
				"			}");
	}};
	
	public static List<String> getFactorFunctionLst() {
		return factorFunctionLst;
	}
}
