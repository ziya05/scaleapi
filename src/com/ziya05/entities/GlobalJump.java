package com.ziya05.entities;

import java.io.Serializable;

public class GlobalJump implements Serializable {
	private static final long serialVersionUID = 1L; 

	private int scaleId;
	private String name;
	private int begin;
	private int end;
	private int continuous;
	private int questionCount;
	private double score;
	private int jumpNo;
	public int getScaleId() {
		return scaleId;
	}
	public void setScaleId(int scaleId) {
		this.scaleId = scaleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getContinuous() {
		return continuous;
	}
	public void setContinuous(int continuous) {
		this.continuous = continuous;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getJumpNo() {
		return jumpNo;
	}
	public void setJumpNo(int jumpNo) {
		this.jumpNo = jumpNo;
	}
	
	
}
