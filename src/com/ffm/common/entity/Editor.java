package com.ffm.common.entity;

import java.util.Map;

public class Editor {
	private String type;
	private Map<String, String> options;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
}