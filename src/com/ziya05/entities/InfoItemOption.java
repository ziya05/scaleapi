package com.ziya05.entities;

import java.io.Serializable;

public class InfoItemOption implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private String option;
	
	public InfoItemOption() {}

	public InfoItemOption(String name, String option) {
		super();
		this.name = name;
		this.option = option;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

}
