package com.ziya05.entities;

import java.io.Serializable;

public class FactorResult implements Serializable {
	private static final long serialVersionUID = 1L; 
	private String name;
	private String description;
	private String advice;
	
	public FactorResult() {
		super();
	}

	public FactorResult(String name, String description, String advice) {
		super();
		this.name = name;
		this.description = description;
		this.advice = advice;
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
}
