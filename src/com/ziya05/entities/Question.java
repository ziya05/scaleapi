package com.ziya05.entities;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int id;
	private String title;
	private List<Option> items;
	
	public Question() {}
	public Question(int id, String title, List<Option> items) {
		super();
		this.id = id;
		this.title = title;
		this.items = items;
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

	
}
