package com.ziya05.entities;

import java.io.Serializable;
import java.util.List;

public class Factor implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private String formula;
	private List<Level> levelList;
	private int FactorId;
	
	public Factor() {
		super();
	}

	public Factor(String name, String formula, List<Level> levelList) {
		super();
		this.name = name;
		this.formula = formula;
		this.levelList = levelList;
	}

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

	public List<Level> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<Level> levelList) {
		this.levelList = levelList;
	}

	public int getFactorId() {
		return FactorId;
	}

	public void setFactorId(int factorId) {
		FactorId = factorId;
	}

}
