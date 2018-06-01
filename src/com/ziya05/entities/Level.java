package com.ziya05.entities;

import java.io.Serializable;

public class Level implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int minValue;
	private int maxValue;
	private String name;
	private String description;
	
	public Level() {
		super();
	}

	public Level(int minValue, int maxValue, String name, String description) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.name = name;
		this.description = description;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
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
	
}
