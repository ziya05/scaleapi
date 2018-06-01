package com.ziya05.entities;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {
	private static final long serialVersionUID = 1L; 
	private String description;
	private UserHistoryData data;
	
	private List<FactorPair> items;

	public Result() {
		super();
	}

	public Result(List<FactorPair> items, String description, UserHistoryData data) {
		super();
		this.items = items;
		this.description = description;
		this.data = data;
	}

	public List<FactorPair> getItems() {
		return items;
	}

	public void setItems(List<FactorPair> items) {
		this.items = items;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserHistoryData getData() {
		return data;
	}

	public void setData(UserHistoryData data) {
		this.data = data;
	}
	
	
}
