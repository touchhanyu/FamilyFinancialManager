package com.ffm.stock.entity;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("")
public class StockMarket {
	/* 成交量 */
	@Column("")
	private String dealNum;
	/* 成交额 */
	@Column("")
	private String dealPri;
	/* 最高 */
	@Column("")
	private String highPri;
	/* 最低 */
	@Column("")
	private String lowpri;
	/* 名称 */
	@Column("")
	private String name;
	/* 当前价格 */
	@Column("")
	private String nowpri;
	/* 今开 */
	@Column("")
	private String openPri;
	/* 时间 */
	@Column("")
	private String time;
	/* 昨收 */
	@Column("")
	private String yesPri;

	public String getDealNum() {
		return dealNum;
	}

	public void setDealNum(String dealNum) {
		this.dealNum = dealNum;
	}

	public String getDealPri() {
		return dealPri;
	}

	public void setDealPri(String dealPri) {
		this.dealPri = dealPri;
	}

	public String getHighPri() {
		return highPri;
	}

	public void setHighPri(String highPri) {
		this.highPri = highPri;
	}

	public String getLowpri() {
		return lowpri;
	}

	public void setLowpri(String lowpri) {
		this.lowpri = lowpri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNowpri() {
		return nowpri;
	}

	public void setNowpri(String nowpri) {
		this.nowpri = nowpri;
	}

	public String getOpenPri() {
		return openPri;
	}

	public void setOpenPri(String openPri) {
		this.openPri = openPri;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getYesPri() {
		return yesPri;
	}

	public void setYesPri(String yesPri) {
		this.yesPri = yesPri;
	}
}