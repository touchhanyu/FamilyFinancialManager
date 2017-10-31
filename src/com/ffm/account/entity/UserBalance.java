package com.ffm.account.entity;

import java.util.Date;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FFM_ACCOUNT_USERBALANCE")
public class UserBalance {
	/* 资产编号 */
	@Column("ID")
	private Integer id;
	/* 资产类型 */
	@Column("TYPE")
	private String type;
	/* 资产价值 */
	@Column("WORTH")
	private Double worth;
	/* 用户编号 */
	@Column("USERID")
	private Integer userId;
	/* 创建日期 */
	@Column("MAKEDATE")
	private Date makeDate;
	/* 创建时间 */
	@Column("MAKETIME")
	private String makeTime;
	/* 操作员 */
	@Column("OPERID")
	private Integer operId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getWorth() {
		return worth;
	}

	public void setWorth(Double worth) {
		this.worth = worth;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getOperId() {
		return operId;
	}

	public void setOperId(Integer operId) {
		this.operId = operId;
	}
}