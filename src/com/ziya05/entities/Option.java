package com.ziya05.entities;

import java.io.Serializable;

public class Option implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int id;
	private String content;
	private int score = 0;
	private int next = -1;
	private int questionId;
	
	public Option() {}
	
	public Option(int id, String content, int score) {
		super();
		this.id = id;
		this.content = content;
		this.score = score;
	}

	public Option(int id, String content, int score, int next) {
		super();
		this.id = id;
		this.content = content;
		this.next = next;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
