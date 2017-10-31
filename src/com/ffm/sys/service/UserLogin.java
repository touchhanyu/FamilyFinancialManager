package com.ffm.sys.service;

import java.util.List;

import com.ffm.sys.entity.SysUser;

public interface UserLogin {
	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	List<SysUser> login(SysUser user);

	/**
	 * 用户注册
	 * 
	 * @param user
	 */
	void register(SysUser user);
}