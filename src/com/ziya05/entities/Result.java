package com.ziya05.entities;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {
	private static final long serialVersionUID = 1L; 
	private String description;
	private UserHistoryData data;
	private List<String> groupLst;
	
	private List<FactorResult> items;

	public Result() {
		super();
	}

	public Result(List<FactorResult> items, String description, UserHistoryData data, List<String> groupLst) {
		super();
		this.items = items;
		this.description = description;
		this.data = data;
		this.groupLst = groupLst;
	}

	public List<FactorResult> getItems() {
		return items;
	}

	public void setItems(List<FactorResult> items) {
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

	public List<String> getGroupLst() {
		return groupLst;
	}

	public void setGroupLst(List<String> groupLst) {
		this.groupLst = groupLst;
	}
}
