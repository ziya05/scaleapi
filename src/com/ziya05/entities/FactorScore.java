package com.ziya05.entities;

public class FactorScore {
	private int factorId;
	private double score;
	
	public FactorScore() {
		super();
	}

	public FactorScore(int factorId, double score) {
		super();
		this.factorId = factorId;
		this.score = score;
	}

	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
