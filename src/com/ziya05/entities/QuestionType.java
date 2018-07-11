package com.ziya05.entities;

public enum QuestionType {
	
	SINGLE(1), MULTPLE(2), DESCRIPTION(4);
	
	private int nCode;
	
	private QuestionType(int nCode) {
		this.nCode = nCode;
	}
	
	public String toString() {
		return String.valueOf(this.nCode);
	}
}
