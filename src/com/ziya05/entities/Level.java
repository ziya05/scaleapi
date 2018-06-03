package com.ziya05.entities;

import java.io.Serializable;

public class Level implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private int factorId;
	private int levelId;
	private String description;
	private String advice;
	
	public Level() {
		super();
	}

	public Level(int factorId, int levelId, String description, String advice) {
		super();
		this.factorId = factorId;
		this.levelId = levelId;
		this.description = description;
		this.advice = advice;
	}

	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}
	
}
