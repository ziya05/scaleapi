package com.ziya05.entities;

import java.io.Serializable;

public class Option implements Serializable, Comparable<Option> {
	private static final long serialVersionUID = 1L; 
	private String optionId;
	private String content;
	private int score = -1;
	private int next = -1;
	private int questionId;
	
	public Option() {}
	
	public Option(String optionId, String content, int score) {
		super();
		this.optionId = optionId;
		this.content = content;
		this.score = score;
	}

	public Option(String optionId, String content, int score, int next) {
		super();
		this.optionId = optionId;
		this.content = content;
		this.next = next;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}	

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	@Override
	public int compareTo(Option arg0) {
		if (this.optionId.compareTo(arg0.getOptionId()) > 0) {
			return 1;
		} else if (this.optionId.compareTo(arg0.getOptionId()) < 0) {
			return -1;
		} else {
			return 0;
		}
	}	
	
}
