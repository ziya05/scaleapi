package com.ziya05.entities;

import java.io.Serializable;

public class UserHistoryData implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private PersonalInfo info;
	private SelectedData data;
	
	public UserHistoryData() {
		super();
	}

	public UserHistoryData(PersonalInfo info, SelectedData data) {
		super();
		this.info = info;
		this.data = data;
	}

	public PersonalInfo getInfo() {
		return info;
	}

	public void setInfo(PersonalInfo info) {
		this.info = info;
	}

	public SelectedData getData() {
		return data;
	}

	public void setData(SelectedData data) {
		this.data = data;
	}

}
