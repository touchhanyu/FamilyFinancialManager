package com.ffm.sys.entity;

import java.util.List;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FFM_SYS_MENU")
public class Menu {
	/* 菜单编号 */
	@Column("ID")
	private int id;
	/* 菜单名称 */
	@Column("TEXT")
	private String text;
	/* 菜单图标 */
	@Column("ICON")
	private String iconCls;
	/* 菜单路径 */
	@Column("PATH")
	private String href;
	/* 菜单状态 */
	@Column("STATE")
	private String state;
	/* 父菜单编号 */
	@Column("PID")
	private String pid;
	/* 菜单顺序 */
	@Column("MORDER")
	private String mOrder;
	/* 子菜单 */
	private List<Menu> children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getmOrder() {
		return mOrder;
	}

	public void setmOrder(String mOrder) {
		this.mOrder = mOrder;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
}