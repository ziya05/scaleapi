package com.ziya05.entities;

import java.io.Serializable;

public class FactorResult implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int factorId;
	private String name;
	private double score;
	private int levelId;
	private String description;
	private String advice;
	
	public FactorResult() {
		super();
	}

	public FactorResult(int factorId, String name, double score, int levelId, String description, String advice) {
		super();
		this.factorId = factorId;
		this.name = name;
		this.score = score;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	
	
}
