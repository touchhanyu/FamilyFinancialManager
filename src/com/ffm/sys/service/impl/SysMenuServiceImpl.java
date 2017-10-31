package com.ffm.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ffm.common.db.SQLUtil;
import com.ffm.common.util.COMUtil;
import com.ffm.sys.dao.SysAuthorityDAO;
import com.ffm.sys.entity.Menu;
import com.ffm.sys.entity.SysAuthority;
import com.ffm.sys.entity.SysUser;
import com.ffm.sys.service.SysMenuService;

public class SysMenuServiceImpl implements SysMenuService {
	private SysAuthorityDAO dao;

	public void setDao(SysAuthorityDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Menu> showMenuList() {
		// TODO Auto-generated method stub
		String sql = "select * from ffm_sys_menu where pid is null and state='1' order by morder";
		List<Menu> menus = SQLUtil.query(sql, Menu.class);
		for (int i = 0; i < menus.size(); i++) {
			Menu menu = menus.get(i);
			this.findSubMenu(menu);
		}
		return menus;
	}

	public void findSubMenu(Menu menu) {
		String sql = "select * from ffm_sys_menu where pid='" + menu.getId() + "' order by morder";
		List<Menu> children = SQLUtil.query(sql, Menu.class);
		if (children == null || children.size() < 1) {
			menu.setState("false");
			return;
		}
		for (int i = 0; i < children.size(); i++) {
			Menu subMenu = children.get(i);
			this.findSubMenu(subMenu);
		}
		menu.setChildren(children);
		menu.setState("true");
	}

	@Override
	public void menuAuthority(String ids, SysUser user) {
		// TODO Auto-generated method stub
		Date date = new Date();
		String currentTime = COMUtil.currentTime();
		String[] mids = ids.split(",");
		List<SysAuthority> list = new ArrayList<SysAuthority>();
		for (int i = 0; i < mids.length; i++) {
			String mid = mids[i];
			SysAuthority sysAuthority = new SysAuthority();
			sysAuthority.setMenuId(Integer.parseInt(mid));
			sysAuthority.setUserId(user.getId());
			sysAuthority.setMakeDate(date);
			sysAuthority.setMakeTime(currentTime);
			sysAuthority.setOperId(user.getId());
			list.add(sysAuthority);
		}
		this.dao.insert(list);
	}
}