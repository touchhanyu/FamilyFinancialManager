package com.ffm.currency.entity;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FI_CURRENCY_PRICE")
public class Currency {
	@Column("ID")
	private Integer id;
	/* 货币名称 */
	@Column("NAME")
	private String name;
	/* 现汇买入价 */
	@Column("FBUYFRI")
	private String fBuyPri;
	/* 现钞买入价 */
	@Column("MBUYPRI")
	private String mBuyPri;
	/* 现汇卖出价 */
	@Column("FSELLPRI")
	private String fSellPri;
	/* 现钞卖出价 */
	@Column("MSELLPRI")
	private String mSellPri;
	/* 银行折算价/中间价 */
	@Column("BANKCONVERSIONPRI")
	private String bankConversionPri;
	/* 发布日期 */
	@Column("PUBLICDATE")
	private String date;
	/* 发布时间 */
	@Column("PUBLICTIME")
	private String time;
	/* 创建日期 */
	@Column("MAKEDATE")
	private String makeDate;
	/* 创建时间 */
	@Column("MAKETIME")
	private String makeTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getfBuyPri() {
		return fBuyPri;
	}

	public void setfBuyPri(String fBuyPri) {
		this.fBuyPri = fBuyPri;
	}

	public String getmBuyPri() {
		return mBuyPri;
	}

	public void setmBuyPri(String mBuyPri) {
		this.mBuyPri = mBuyPri;
	}

	public String getfSellPri() {
		return fSellPri;
	}

	public void setfSellPri(String fSellPri) {
		this.fSellPri = fSellPri;
	}

	public String getmSellPri() {
		return mSellPri;
	}

	public void setmSellPri(String mSellPri) {
		this.mSellPri = mSellPri;
	}

	public String getBankConversionPri() {
		return bankConversionPri;
	}

	public void setBankConversionPri(String bankConversionPri) {
		this.bankConversionPri = bankConversionPri;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(String makeDate) {
		this.makeDate = makeDate;
	}

	public String getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}
}