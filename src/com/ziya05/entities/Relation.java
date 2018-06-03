package com.ziya05.entities;

import java.io.Serializable;

public class Relation implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private int factorId;
	private int groundId;
	private String points;
	
	public Relation() {
		super();
	}
	
	public Relation(int factorId, int groundId, String points) {
		super();
		this.factorId = factorId;
		this.groundId = groundId;
		this.points = points;
	}

	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public int getGroundId() {
		return groundId;
	}

	public void setGroundId(int groundId) {
		this.groundId = groundId;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
}
