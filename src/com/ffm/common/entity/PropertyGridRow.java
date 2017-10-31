package com.ffm.common.entity;

import com.ffm.common.annotation.Column;

public class PropertyGridRow {
	@Column("ID")
	private Integer id;
	@Column("DICTCODE")
	private String dictCode;
	@Column("NAME")
	private String name;
	@Column("VALUE")
	private String value;
	@Column("TOTAL")
	private Double total;
	@Column("GROUP")
	private String group;
//	@Column("EDITOR")
	private Editor editor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}
}