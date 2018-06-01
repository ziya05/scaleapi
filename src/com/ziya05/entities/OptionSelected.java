package com.ziya05.entities;

import java.io.Serializable;

public class OptionSelected implements Serializable {
	private static final long serialVersionUID = 1L; 

	private int questionId;
	private int optionId;
	private int score;
	
	public OptionSelected() {
		super();
	}

	public OptionSelected(int questionId, int optionId, int score) {
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

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
