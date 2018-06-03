package com.ziya05.entities;

import java.io.Serializable;

public class Group implements Serializable {
	private static final long serialVersionUID = 1L; 

	private int groupId;
	private String name;
	private String formula;
	
	public Group() {
		super();
	}

	public Group(int groupId, String name, String formula) {
		super();
		this.groupId = groupId;
		this.name = name;
		this.formula = formula;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
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
}
