package com.ffm.account.entity;

import java.util.Date;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FFM_ACCOUNT_DAYBOOK")
public class DayBook {
	/* 流水号 */
	@Column("ID")
	private String id;
	/* 资金方向 */
	@Column("DIRECTION")
	private String direction;
	/* 资金形式 */
	@Column("TYPE")
	private String type;
	/* 金额 */
	@Column("AMOUNT")
	private Double amount;
	/* 用途 */
	@Column("PURPOSE")
	private String purpose;
	/* 备注 */
	@Column("REMARK")
	private String remark;
	/* 交易日期 */
	@Column("TRANDATE")
	private Date tranDate;
	/* 交易时间 */
	@Column("TRANTIME")
	private String tranTime;
	/* 交易用户 */
	@Column("TRANOPER")
	private Integer tranOper;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public Integer getTranOper() {
		return tranOper;
	}

	public void setTranOper(Integer tranOper) {
		this.tranOper = tranOper;
	}
}