package com.ffm.account.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FFM_ACCOUNT_BALANCEDETAIL")
public class BalanceDetail {
	/* 资产明细编号 */
	@Column("ID")
	private Integer id;
	/* 资产编号 */
	@Column("BALANCEID")
	private Integer balanceId;
	/* 资产类型业务表编号 */
	@Column("DETAILID")
	private String detailId;
	/* 交易类型 */
	@Column("TYPE")
	private String type;
	/* 资产单价 */
	@Column("PRICE")
	private Double price;
	/* 资产数量 */
	@Column("NUM")
	private Integer num;
	/* 手续费 */
	@Column("FEE")
	private Double fee;
	/* 发生金额 */
	@Column("AMOUNT")
	private BigDecimal amount;
	/* 剩余金额 */
	@Column("OUTSTANDING")
	private BigDecimal outstanding;
	/* 剩余数量 */
	@Column("REAMAINNUM")
	private Integer remainNum;
	/* 状态 */
	@Column("STATUS")
	private String status;
	/* 交易日期 */
	@Column("TRANDATE")
	private Date tranDate;
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

	public Integer getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(BigDecimal outstanding) {
		this.outstanding = outstanding;
	}

	public Integer getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
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