package com.ffm.common.entity;

import java.util.List;

public class PropertyGrid {
	private int total;
	private List<PropertyGridRow> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<PropertyGridRow> getRows() {
		return rows;
	}

	public void setRows(List<PropertyGridRow> rows) {
		this.rows = rows;
	}
}