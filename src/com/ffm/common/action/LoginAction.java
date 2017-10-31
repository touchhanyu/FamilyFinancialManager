package com.ffm.common.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ffm.common.util.SysUtil;
import com.opensymphony.xwork2.Action;

public class LoginAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
		String[] data = SysUtil.checkCookies(cookies);
		if (data[0] == null || data[1] == null) {
			return ERROR;
		}
		return SUCCESS;
	}
}