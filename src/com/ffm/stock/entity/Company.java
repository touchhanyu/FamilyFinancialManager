package com.ffm.stock.entity;

import java.util.Date;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FFM_STOCK_COMPANY")
public class Company {
	/* 公司编号 */
	@Column("ID")
	private Integer id;
	/* 公司名称 */
	@Column("NAME")
	private String name;
	/* 公司拼音 */
	@Column("PINYIN")
	private String pinyin;
	/* 法人 */
	@Column("LEGALNAME")
	private String legalName;
	/* 组织机构代码 */
	@Column("ORGID")
	private String orgId;
	/* 股票代码 */
	@Column("GID")
	private String gid;
	/* 股票代码 */
	@Column("GNAME")
	private String gname;
	/* 股票代码 */
	@Column("GTYPE")
	private String gtype;
	/* 创建日期 */
	@Column("MAKEDATE")
	private Date makeDate;
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

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
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