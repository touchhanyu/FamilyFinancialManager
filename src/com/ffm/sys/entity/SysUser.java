package com.ffm.sys.entity;

import java.util.Date;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FFM_SYS_USER")
public class SysUser {
	/* 用户编号 */
	@Column("ID")
	private Integer id;
	/* 用户名称 */
	@Column("NAME")
	private String name;
	/* 用户密码 */
	@Column("PASSWORD")
	private String password;
	/* 真实姓名 */
	@Column("REALNAME")
	private String realName;
	/* 证件号码 */
	@Column("IDCARD")
	private String idCard;
	/* 证件类型 */
	@Column("CARDTYPE")
	private String cardType;
	/* 性别 */
	@Column("SEX")
	private String sex;
	/* 国籍 */
	@Column("NATIONALID")
	private Integer nationalId;
	/* 民族 */
	@Column("CULTRUE")
	private String cultrue;
	/* 手机号 */
	@Column("TELPHONE")
	private String telphone;
	/* 电话号码 */
	@Column("PHONE")
	private String phone;
	/* 电子邮箱 */
	@Column("EMAIL")
	private String email;
	/* QQ号 */
	@Column("QQ")
	private String qq;
	/* 城市编号 */
	@Column("CITYID")
	private Integer cityId;
	/* 家庭住址 */
	@Column("HOMEADDRESS")
	private String homeAddress;
	/* 工作地址 */
	@Column("OFFICEADDRESS")
	private String officeAddress;
	/* 用户权限 */
	@Column("AUTHORITY")
	private String authority;
	/* 注册日期 */
	@Column("MAKEDATE")
	private Date makeDate;
	/* 注册时间 */
	@Column("MAKETIME")
	private String makeTime;
	/* 修改日期 */
	@Column("MODIFYDATE")
	private Date modifyDate;
	/* 修改时间 */
	@Column("MODIFYTIME")
	private String modifyTime;
	/* 操作员 */
	@Column("OPERID")
	private Integer operId;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getNationalId() {
		return nationalId;
	}

	public void setNationalId(Integer nationalId) {
		this.nationalId = nationalId;
	}

	public String getCultrue() {
		return cultrue;
	}

	public void setCultrue(String cultrue) {
		this.cultrue = cultrue;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
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

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getOperId() {
		return operId;
	}

	public void setOperId(Integer operId) {
		this.operId = operId;
	}
}