package com.ffm.entitymaker.entity;

import java.util.List;

/**
 * @author Hanyu
 * 
 */
public class TableEntity {

	private String name;
	private String code;
	private String comments;
	private List<Colunm> colunms;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<Colunm> getColunms() {
		return colunms;
	}

	public void setColunms(List<Colunm> colunms) {
		this.colunms = colunms;
	}
}