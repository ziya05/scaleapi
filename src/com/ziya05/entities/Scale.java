package com.ziya05.entities;

import java.util.List;
import java.io.Serializable;

public class Scale implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int id;
	private String name;
	private int questionCount;
	private String description;
	private List<Question> items;
	
	public Scale() {}
	
	public Scale(int id, String name, int questionCount, String description) {
		super();
		this.id = id;
		this.name = name;
		this.questionCount = questionCount;
		this.description = description;
	}
	
	public Scale(int id, String name, int questionCount, String description, List<Question> items) {
		super();
		this.id = id;
		this.name = name;
		this.questionCount = questionCount;
		this.description = description;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Question> getItems() {
		return items;
	}

	public void setItems(List<Question> items) {
		this.items = items;
	}
	
}
