package com.ziya05.entities;

import java.io.Serializable;

public class Level implements Serializable {
	private static final long serialVersionUID = 1L; 
	private double minValue;
	private double maxValue;
	private String name;
	private String description;
	private int factorId;
	
	public Level() {
		super();
	}

	public Level(double minValue, double maxValue, String name, String description) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.name = name;
		this.description = description;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
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

	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}
	
}
