package com.ziya05.entities;

import java.io.Serializable;
import java.util.List;

public class PersonalInfo  implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private String gender;
	private double age;
	
	private List<InfoItem> items;

	public PersonalInfo() {
		super();
	}

	public PersonalInfo(String name, String gender, double age, List<InfoItem> items) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
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

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public List<InfoItem> getItems() {
		return items;
	}

	public void setItems(List<InfoItem> items) {
		this.items = items;
	}
	
}
