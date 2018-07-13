package com.ziya05.entities;

import java.io.Serializable;
import java.util.List;

public class Factor implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private String formula;
	private int FactorId;
	private int levelCount;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public int getFactorId() {
		return FactorId;
	}

	public void setFactorId(int factorId) {
		FactorId = factorId;
	}

	public int getLevelCount() {
		return levelCount;
	}

	public void setLevelCount(int levelCount) {
		this.levelCount = levelCount;
	}

	
}
