package com.ffm.stock.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.ffm.common.annotation.Column;

public class StockQuota {
	@Column("GID")
	private String gid;
	@Column("NAME")
	private String name;
	@Column("PUBLICDATE")
	private Date publicDate;
	@Column("TODAYMAX")
	private Double todayMax;
	@Column("TODAYMIN")
	private Double todayMin;
	@Column("NOWPRI")
	private Double nowPri;
	@Column("TRANUMBER")
	private Integer traNumber;
	@Column("TRAAMOUNT")
	private BigDecimal traAmount;
	@Column("AVGPRI")
	private double avgPri;
	@Column("SUB1")
	private double sub1;

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPublicDate() {
		return publicDate;
	}

	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}

	public Double getTodayMax() {
		return todayMax;
	}

	public void setTodayMax(Double todayMax) {
		this.todayMax = todayMax;
	}

	public Double getTodayMin() {
		return todayMin;
	}

	public void setTodayMin(Double todayMin) {
		this.todayMin = todayMin;
	}

	public Double getNowPri() {
		return nowPri;
	}

	public void setNowPri(Double nowPri) {
		this.nowPri = nowPri;
	}

	public Integer getTraNumber() {
		return traNumber;
	}

	public void setTraNumber(Integer traNumber) {
		this.traNumber = traNumber;
	}

	public BigDecimal getTraAmount() {
		return traAmount;
	}

	public void setTraAmount(BigDecimal traAmount) {
		this.traAmount = traAmount;
	}

	public double getAvgPri() {
		return avgPri;
	}

	public void setAvgPri(double avgPri) {
		this.avgPri = avgPri;
	}

	public double getSub1() {
		return sub1;
	}

	public void setSub1(double sub1) {
		this.sub1 = sub1;
	}
}