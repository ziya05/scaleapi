package com.ziya05.entities;

import java.io.Serializable;

public class InfoItem implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private String title;
	private String content;
	
	public InfoItem() {
		super();
	}
	
	public InfoItem(String name, String title) {
		super();
		this.name = name;
		this.title = title;
	}
	
	public InfoItem(String name, String title, String content) {
		super();
		this.name = name;
		this.title = title;
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
