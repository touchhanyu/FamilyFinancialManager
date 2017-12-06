package com.ffm.common.entity;

import java.util.Date;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FFM_SYS_DICTIONARYDETAIL")
public class DictionaryDetail {
	/* 字典明细编号 */
	@Column("ID")
	private int id;
	/* 字典编号 */
	@Column("DID")
	private int dId;
	/* 字典代码 */
	@Column("DICTCODE")
	private String dictCode;
	/* 字典明细值 */
	@Column("DICTVALUE")
	private String dictValue;
	/* 字典明细值 */
	@Column("TEXT")
	private String text;
	/* 显示顺序 */
	@Column("DORDER")
	private String dOrder;
	/* 使用状态 */
	@Column("STATE")
	private String state;
	/* 创建日期 */
	@Column("MAKEDATE")
	private Date makeDate;
	/* 创建时间 */
	@Column("MAKETIME")
	private String makeTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getdOrder() {
		return dOrder;
	}

	public void setdOrder(String dOrder) {
		this.dOrder = dOrder;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}
}