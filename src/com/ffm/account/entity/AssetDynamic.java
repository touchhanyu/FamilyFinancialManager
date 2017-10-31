package com.ffm.account.entity;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FFM_Account_AssetDynamic")
public class AssetDynamic {
	/* 资产编号 */
	@Column("BALANCEID")
	private Integer balanceId;
	/* 资产类型业务表编号 */
	@Column("DETAILID")
	private String detailId;
	/* 资产价值合计 */
	@Column("TOTAL")
	private Double total;
	/* 资产数量 */
	@Column("NUM")
	private Integer num;
	/* 资产均值 */
	@Column("AVERAGEPRICE")
	private Double averagePrice;

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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
}