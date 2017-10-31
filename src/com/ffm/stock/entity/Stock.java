package com.ffm.stock.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Comment;
import com.ffm.common.annotation.Table;

@Table("FFM_STOCK_STOCK")
public class Stock {
	/* 上市地 */
	@Comment("上市地")
	@Column("GTYPE")
	private String gType;
	/* 股票编号 */
	@Comment("股票编号")
	@Column("GID")
	private String gid;
	/* 股票名称 */
	@Comment("股票名称")
	@Column("NAME")
	private String name;
	/* 今日开盘价 */
	@Comment("今日开盘价")
	@Column("TODAYSTARTPRI")
	private Double todayStartPri;
	/* 昨日收盘价 */
	@Column("YESTODENDPRI")
	private Double yestodEndPri;
	/* 当前价格 */
	@Comment("当前价格")
	@Column("NOWPRI")
	private Double nowPri;
	/* 今日最高价 */
	@Comment("今日最高价")
	@Column("TODAYMAX")
	private Double todayMax;
	/* 今日最低价 */
	@Comment("今日最低价")
	@Column("TODAYMIN")
	private Double todayMin;
	/* 竞买价 */
	@Comment("竞买价")
	@Column("COMPETITIVEPRI")
	private Double competitivePri;
	/* 竞卖价 */
	@Comment("竞卖价")
	@Column("RESERVEPRI")
	private Double reservePri;
	/* 成交量 */
	@Comment("成交量")
	@Column("TRANUMBER")
	private BigDecimal traNumber;
	/* 成交金额 */
	@Comment("成交金额")
	@Column("TRAAMOUNT")
	private BigDecimal traAmount;
	/* 买一 */
	@Comment("买一")
	@Column("BUY1")
	private Integer buyOne;
	/* 买一报价 */
	@Comment("买一报价")
	@Column("BUY1PRI")
	private Double buyOnePri;
	/* 买二 */
	@Comment("买二")
	@Column("BUY2")
	private Integer buyTwo;
	/* 买二报价 */
	@Comment("买二报价")
	@Column("BUY2PRI")
	private Double buyTwoPri;
	/* 买三 */
	@Comment("买三")
	@Column("BUY3")
	private Integer buyThree;
	/* 买三报价 */
	@Comment("买三报价")
	@Column("BUY3PRI")
	private Double buyThreePri;
	/* 买四 */
	@Comment("买四")
	@Column("BUY4")
	private Integer buyFour;
	/* 买四报价 */
	@Comment("买四报价")
	@Column("BUY4PRI")
	private Double buyFourPri;
	/* 买五 */
	@Comment("买五")
	@Column("BUY5")
	private Integer buyFive;
	/* 买五报价 */
	@Comment("买五报价")
	@Column("BUY5PRI")
	private Double buyFivePri;
	/* 卖一 */
	@Comment("卖一")
	@Column("SELL1")
	private Integer sellOne;
	/* 卖一报价 */
	@Comment("卖一报价")
	@Column("SELL1PRI")
	private Double sellOnePri;
	/* 卖二 */
	@Comment("卖二")
	@Column("SELL2")
	private Integer sellTwo;
	/* 卖二报价 */
	@Comment("卖四报价")
	@Column("SELL2PRI")
	private Double sellTwoPri;
	/* 卖三 */
	@Comment("卖三")
	@Column("SELL3")
	private Integer sellThree;
	/* 卖三报价 */
	@Comment("卖三报价")
	@Column("SELL3PRI")
	private Double sellThreePri;
	/* 卖四 */
	@Comment("卖四")
	@Column("SELL4")
	private Integer sellFour;
	/* 卖四报价 */
	@Comment("卖四报价")
	@Column("SELL4PRI")
	private Double sellFourPri;
	/* 卖五 */
	@Comment("卖五")
	@Column("SELL5")
	private Integer sellFive;
	/* 卖五报价 */
	@Comment("卖五报价")
	@Column("SELL5PRI")
	private Double sellFivePri;
	/* 发布日期 */
	@Comment("发布日期")
	@Column("PUBLICDATE")
	private Date date;
	/* 发布时间 */
	@Comment("发布时间")
	@Column("PUBLICTIME")
	private String time;
	/* 创建日期 */
	@Column("MAKEDATE")
	private Date makeDate;
	/* 创建时间 */
	@Column("MAKETIME")
	private String makeTime;

	public String getgType() {
		return gType;
	}

	public void setgType(String gType) {
		this.gType = gType;
	}

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

	public Double getTodayStartPri() {
		return todayStartPri;
	}

	public void setTodayStartPri(Double todayStartPri) {
		this.todayStartPri = todayStartPri;
	}

	public Double getYestodEndPri() {
		return yestodEndPri;
	}

	public void setYestodEndPri(Double yestodEndPri) {
		this.yestodEndPri = yestodEndPri;
	}

	public Double getNowPri() {
		return nowPri;
	}

	public void setNowPri(Double nowPri) {
		this.nowPri = nowPri;
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

	public Double getCompetitivePri() {
		return competitivePri;
	}

	public void setCompetitivePri(Double competitivePri) {
		this.competitivePri = competitivePri;
	}

	public Double getReservePri() {
		return reservePri;
	}

	public void setReservePri(Double reservePri) {
		this.reservePri = reservePri;
	}

	public BigDecimal getTraNumber() {
		return traNumber;
	}

	public void setTraNumber(BigDecimal traNumber) {
		this.traNumber = traNumber;
	}

	public BigDecimal getTraAmount() {
		return traAmount;
	}

	public void setTraAmount(BigDecimal traAmount) {
		this.traAmount = traAmount;
	}

	public Integer getBuyOne() {
		return buyOne;
	}

	public void setBuyOne(Integer buyOne) {
		this.buyOne = buyOne;
	}

	public Double getBuyOnePri() {
		return buyOnePri;
	}

	public void setBuyOnePri(Double buyOnePri) {
		this.buyOnePri = buyOnePri;
	}

	public Integer getBuyTwo() {
		return buyTwo;
	}

	public void setBuyTwo(Integer buyTwo) {
		this.buyTwo = buyTwo;
	}

	public Double getBuyTwoPri() {
		return buyTwoPri;
	}

	public void setBuyTwoPri(Double buyTwoPri) {
		this.buyTwoPri = buyTwoPri;
	}

	public Integer getBuyThree() {
		return buyThree;
	}

	public void setBuyThree(Integer buyThree) {
		this.buyThree = buyThree;
	}

	public Double getBuyThreePri() {
		return buyThreePri;
	}

	public void setBuyThreePri(Double buyThreePri) {
		this.buyThreePri = buyThreePri;
	}

	public Integer getBuyFour() {
		return buyFour;
	}

	public void setBuyFour(Integer buyFour) {
		this.buyFour = buyFour;
	}

	public Double getBuyFourPri() {
		return buyFourPri;
	}

	public void setBuyFourPri(Double buyFourPri) {
		this.buyFourPri = buyFourPri;
	}

	public Integer getBuyFive() {
		return buyFive;
	}

	public void setBuyFive(Integer buyFive) {
		this.buyFive = buyFive;
	}

	public Double getBuyFivePri() {
		return buyFivePri;
	}

	public void setBuyFivePri(Double buyFivePri) {
		this.buyFivePri = buyFivePri;
	}

	public Integer getSellOne() {
		return sellOne;
	}

	public void setSellOne(Integer sellOne) {
		this.sellOne = sellOne;
	}

	public Double getSellOnePri() {
		return sellOnePri;
	}

	public void setSellOnePri(Double sellOnePri) {
		this.sellOnePri = sellOnePri;
	}

	public Integer getSellTwo() {
		return sellTwo;
	}

	public void setSellTwo(Integer sellTwo) {
		this.sellTwo = sellTwo;
	}

	public Double getSellTwoPri() {
		return sellTwoPri;
	}

	public void setSellTwoPri(Double sellTwoPri) {
		this.sellTwoPri = sellTwoPri;
	}

	public Integer getSellThree() {
		return sellThree;
	}

	public void setSellThree(Integer sellThree) {
		this.sellThree = sellThree;
	}

	public Double getSellThreePri() {
		return sellThreePri;
	}

	public void setSellThreePri(Double sellThreePri) {
		this.sellThreePri = sellThreePri;
	}

	public Integer getSellFour() {
		return sellFour;
	}

	public void setSellFour(Integer sellFour) {
		this.sellFour = sellFour;
	}

	public Double getSellFourPri() {
		return sellFourPri;
	}

	public void setSellFourPri(Double sellFourPri) {
		this.sellFourPri = sellFourPri;
	}

	public Integer getSellFive() {
		return sellFive;
	}

	public void setSellFive(Integer sellFive) {
		this.sellFive = sellFive;
	}

	public Double getSellFivePri() {
		return sellFivePri;
	}

	public void setSellFivePri(Double sellFivePri) {
		this.sellFivePri = sellFivePri;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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