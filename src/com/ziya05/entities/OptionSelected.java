package com.ziya05.entities;

import java.io.Serializable;

public class OptionSelected implements Serializable, Comparable<OptionSelected> {
	private static final long serialVersionUID = 1L; 

	private int questionId;
	private String optionId;
	private int score;
	
	public OptionSelected() {
		super();
	}

	public OptionSelected(int questionId, String optionId, int score) {
		super();
		this.questionId = questionId;
		this.optionId = optionId;
		this.score = score;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(OptionSelected o) {
		if (this.questionId > o.questionId) {
			return 1;
		} else if (this.questionId < o.questionId) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
