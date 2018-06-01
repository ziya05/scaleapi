package com.ziya05.entities;

import java.io.Serializable;
import java.util.List;

public class PersonalInfo  implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private String gender;
	
	private List<InfoItem> items;

	public PersonalInfo() {
		super();
	}

	public PersonalInfo(String name, String gender, List<InfoItem> items) {
		super();
		this.name = name;
		this.gender = gender;
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<InfoItem> getItems() {
		return items;
	}

	public void setItems(List<InfoItem> items) {
		this.items = items;
	}
	
}
