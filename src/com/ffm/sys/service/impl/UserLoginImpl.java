package com.ffm.sys.service.impl;

import java.util.List;

import com.ffm.common.db.SQLUtil;
import com.ffm.common.util.PKUtil;
import com.ffm.sys.dao.SysUserDAO;
import com.ffm.sys.entity.SysUser;
import com.ffm.sys.service.UserLogin;

public class UserLoginImpl implements UserLogin {
	private SysUserDAO dao = new SysUserDAO();

	public void setDao(SysUserDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<SysUser> login(SysUser user) {
		// TODO Auto-generated method stub
		String sql = "select * from FFM_SYS_USER where NAME='" + user.getName() + "'";
		return SQLUtil.query(sql, SysUser.class);
	}

	@Override
	public void register(SysUser user) {
		// TODO Auto-generated method stub
		Integer id = PKUtil.generatePK("SYS_USER");
		user.setId(id);
		user.setOperId(id);
		this.dao.insert(user);
	}
}