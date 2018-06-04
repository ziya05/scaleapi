package com.ziya05.entities;

import java.io.Serializable;

public class Relation implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private int factorId;
	private int groupId;
	private String points;
	
	public Relation() {
		super();
	}
	
	public Relation(int factorId, int groupId, String points) {
		super();
		this.factorId = factorId;
		this.groupId = groupId;
		this.points = points;
	}

	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
}
