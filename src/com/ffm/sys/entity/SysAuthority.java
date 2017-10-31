package com.ffm.sys.entity;

import java.util.Date;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

/**
 * 用户菜单权限表
 * 
 * @author HanYu
 * 
 */
/**
 * @author HanYu
 * 
 */
@Table("FFM_SYS_AUTHORITY")
public class SysAuthority {
	/* 用户ID */
	@Column("USERID")
	private Integer userId;
	/* 菜单ID */
	@Column("MENUID")
	private Integer menuId;
	/* 操作日期 */
	@Column("MAKEDATE")
	private Date makeDate;
	/* 操作时间 */
	@Column("MAKETIME")
	private String makeTime;
	/* 操作员 */
	@Column("OPERID")
	private Integer operId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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