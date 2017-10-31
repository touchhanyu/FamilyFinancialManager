package com.ffm.account.dto;

import java.util.Date;

import com.ffm.common.annotation.Column;

public class StockAssetDTO {
	/* 资产明细编号 */
	@Column("ID")
	private Integer id;
	/* 资产编号 */
	@Column("BALANCEID")
	private Integer balanceId;
	/* 资产类型业务表编号 */
	@Column("DETAILID")
	private String detailId;
	/* 资产类型业务表名称 */
	@Column("DETAILVALUE")
	private String detailValue;
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
	/* 交易日期 */
	@Column("TRANDATE")
	private Date tranDate;

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

	public String getDetailValue() {
		return detailValue;
	}

	public void setDetailValue(String detailValue) {
		this.detailValue = detailValue;
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

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}
}