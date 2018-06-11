package com.ziya05.entities;

import java.io.Serializable;
import java.util.List;

public class InfoItem implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private String title;
	private int	infoType;
	private String content;
	private List<InfoItemOption> itemOptions;
	
	public InfoItem() {
		super();
	}
	
	public InfoItem(String name, String title, int infoType, List<InfoItemOption> itemOptions) {
		super();
		this.name = name;
		this.title = title;
		this.infoType = infoType;
		this.itemOptions = itemOptions;
	}
	
	public InfoItem(String name, String title, int infoType, String content, List<InfoItemOption> itemOptions) {
		super();
		this.name = name;
		this.title = title;
		this.infoType = infoType;
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

	public List<InfoItemOption> getItemOptions() {
		return itemOptions;
	}

	public void setItemOptions(List<InfoItemOption> itemOptions) {
		this.itemOptions = itemOptions;
	}

	public int getInfoType() {
		return infoType;
	}

	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}

	
}
