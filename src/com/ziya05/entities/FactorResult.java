package com.ziya05.entities;

import java.io.Serializable;

public class FactorResult implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int factorId;
	private String name;
	private double score;
	private int levelId;

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
