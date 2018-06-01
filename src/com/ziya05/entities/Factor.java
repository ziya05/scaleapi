package com.ziya05.entities;

import java.io.Serializable;
import java.util.List;

public class Factor implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private List<Integer> items;
	private List<Level> levelList;
	
	public Factor() {
		super();
	}

	public Factor(String name, List<Integer> items, List<Level> levelList) {
		super();
		this.name = name;
		this.items = items;
		this.levelList = levelList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getItems() {
		return items;
	}

	public void setItems(List<Integer> items) {
		this.items = items;
	}

	public List<Level> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<Level> levelList) {
		this.levelList = levelList;
	}
	
}
