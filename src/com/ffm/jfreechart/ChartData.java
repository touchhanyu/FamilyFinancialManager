package com.ffm.jfreechart;

import com.ffm.common.annotation.Column;

public class ChartData {
	@Column("TYPE")
	private String type;
	@Column("NAME")
	private String name;
	@Column("VALUE")
	private double value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}