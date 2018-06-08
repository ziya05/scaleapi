package com.ziya05.entities;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int id;
	private String title;
	private int questionType;
	private List<Option> items;
	
	public Question() {}
	public Question(int id, String title, int questionType, List<Option> items) {
		super();
		this.id = id;
		this.title = title;
		this.items = items;
		this.questionType = questionType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Option> getItems() {
		return items;
	}
	public void setItems(List<Option> items) {
		this.items = items;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	
}
