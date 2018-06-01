package com.ziya05.entities;

import java.io.Serializable;

public class FactorPair implements Serializable {
	private static final long serialVersionUID = 1L; 
	private String name;
	private String description;
	
	public FactorPair() {
		super();
	}

	public FactorPair(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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
