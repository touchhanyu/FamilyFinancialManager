package com.ffm.sys.action;

import java.io.IOException;
import java.util.List;

import com.ffm.common.action.BaseAction;
import com.ffm.common.db.SQLUtil;
import com.ffm.common.dict.STATUS;
import com.ffm.sys.entity.Menu;
import com.ffm.sys.entity.SysUser;
import com.ffm.sys.service.SysMenuService;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class MenuTreeAction extends BaseAction {
	private String data;
	private String pid;
	private String id;
	private List<Menu> list;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Menu> getList() {
		return list;
	}

	public void setList(List<Menu> list) {
		this.list = list;
	}

	private SysMenuService service;

	public void setService(SysMenuService service) {
		this.service = service;
	}

	/**
	 * 查询各模块菜单
	 * 
	 * @return
	 * @throws IOException
	 */
	public String panelInfo() throws IOException {
		String sql = "select * from FFM_SYS_MENU where PID is null order by MORDER";
		this.list = SQLUtil.query(sql, Menu.class);
		super.setValueOGNL("menus", this.list);
		Object isMobile = super.getSessionAttributes("isMobile");
		if (isMobile != null)
//			return "successMobile";
			return SUCCESS;
		return SUCCESS;
	}

	/**
	 * 菜单管理展示所有菜单
	 * 
	 * @throws IOException
	 */
	public void sysMenu() throws IOException {
		String sql = "select distinct m1.ID,m1.TEXT,m1.ICON,m1.MORDER,(case when m2.ID is not null then 'closed' else 'open' end)STATE from ";
		sql += "(select * from FFM_SYS_MENU where PID";
		if (this.id == null) {
			sql += " is null";
		} else {
			sql += "=" + this.id;
		}
		sql += " and STATE='" + STATUS.Y;
		sql += "')m1 left join FFM_SYS_MENU m2 on m1.ID=m2.PID and m1.STATE=m2.STATE order by m1.MORDER";
		List<Menu> list = SQLUtil.query(sql, Menu.class);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		super.outputJSON(json);
	}

	/**
	 * 查询模块下菜单明细
	 * 
	 * @throws IOException
	 */
	public void menusList() throws IOException {
		if (this.id == null)
			this.id = this.pid;
		SysUser user = super.getSysUser();
		String sql = "select distinct m1.ID,m1.TEXT,m1.ICON,m1.PATH,m1.MORDER,(case when m2.ID is not null then 'closed' else 'open' end)STATE from (select * from FFM_SYS_MENU where PID='"
				+ this.id + "' and STATE='" + STATUS.Y + "'";
		if (!"0".equals(user.getAuthority())) {// 最高权限
//			sql += " and ID in(select MENUID from FFM_SYS_AUTHORITY where USERID=" + user.getId()+ ")";
		}
		sql += ")m1 left join FFM_SYS_MENU m2 on m1.ID=m2.PID and m1.STATE=m2.STATE order by m1.MORDER";
		List<Menu> list = SQLUtil.query(sql, Menu.class);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		super.outputJSON(json);
	}

	/**
	 * 菜单信息维护
	 * 
	 * @throws IOException
	 */
	public void menuEdit() throws IOException {
		
	}

	/**
	 * 用户菜单权限管理
	 * 
	 * @throws IOException
	 */
	public void menuPermission() throws IOException {
		if (this.data != null && !"".equals(this.data)) {
			service.menuAuthority(this.data, super.getSysUser());
		}
	}

	public void showMenuList() throws IOException {
		List<Menu> menus = this.service.showMenuList();
		Gson gson = new Gson();
		String json = gson.toJson(menus);
		super.outputJSON(json);
	}
}