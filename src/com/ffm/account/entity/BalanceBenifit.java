package com.ffm.account.entity;

import java.util.Date;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FFM_ACCOUNT_BALANCEBENIFIT")
public class BalanceBenifit {
	/* 资产收益明细编号 */
	@Column("ID")
	private Integer id;
	/* 资产明细编号 */
	@Column("BALANCEDETAILID")
	private Integer balanceDetailId;
	/* 资产类型业务表编号 */
	@Column("DETAILID")
	private String detailId;
	/* 资产收益 */
	@Column("BENIFIT")
	private Double benifit;
	/* 交易日期 */
	@Column("TRANDATE")
	private Date tranDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBalanceDetailId() {
		return balanceDetailId;
	}

	public void setBalanceDetailId(Integer balanceDetailId) {
		this.balanceDetailId = balanceDetailId;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public Double getBenifit() {
		return benifit;
	}

	public void setBenifit(Double benifit) {
		this.benifit = benifit;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}
}