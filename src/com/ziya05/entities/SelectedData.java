package com.ziya05.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectedData implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private List<OptionSelected> items;

	public SelectedData() {
		super();
		
		this.items = new ArrayList();
	}

	public SelectedData(List<OptionSelected> items) {
		super();
		this.items = items;
	}

	public List<OptionSelected> getItems() {
		return items;
	}

	public void setItems(List<OptionSelected> items) {
		this.items = items;
	}

}
