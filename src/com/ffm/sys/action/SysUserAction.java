package com.ffm.sys.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ffm.common.action.BaseAction;
import com.ffm.common.util.COMUtil;
import com.ffm.common.util.EncryptUtil;
import com.ffm.common.util.SysUtil;
import com.ffm.sys.entity.SysUser;
import com.ffm.sys.service.UserLogin;

@SuppressWarnings("serial")
public class SysUserAction extends BaseAction {
	private SysUser user;
	private String autologin;
	private String clientType;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public String getAutologin() {
		return autologin;
	}

	public void setAutologin(String autologin) {
		this.autologin = autologin;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	private UserLogin userLogin;

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public void login() throws IOException {
		boolean flag = false;// 是否手机端标识
		if (this.clientType != null)
			flag = true;
		List<SysUser> users = this.userLogin.login(this.user);
		if (users.size() == 1) {
			SysUser user = users.get(0);
			String password = user.getPassword();
			String pwd = EncryptUtil.encodeMD5(this.user.getPassword());
			if (!password.equals(pwd))// 密码不正确
//				return ERROR;
				return;
			super.addSession("user", user);
			if (this.autologin != null && "on".equals(this.autologin)) {// 自动登录标志
				HttpServletResponse response = ServletActionContext.getResponse();
				SysUtil.addCookie(response, this.user.getName(), password);
			}
			if (flag) {
//				super.addSession("isMobile", this.clientType);
//				return "successMobile";
			}
			super.outputJSON("{\"res\":\"succ\"}");
			return;
		}
//		if (flag)
//			return "errorMobile";
//		return ERROR;
	}

	public String register() {
		boolean flag = false;// 是否手机端标识
		if (this.clientType != null)
			flag = true;
		List<SysUser> list = this.userLogin.login(this.user);
		if (list.size() > 0)
			return ERROR;
		Date date = new Date();
		String currentTime = COMUtil.currentTime();
		String password = this.user.getPassword();
		password = EncryptUtil.encodeMD5(password);
		this.user.setPassword(password);
		this.user.setMakeDate(date);
		this.user.setMakeTime(currentTime);
		this.user.setAuthority("1");
		try {
			this.userLogin.register(this.user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (flag)
				return "errorMobile";
			return ERROR;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		SysUtil.addCookie(response, this.user.getName(), password);// 添加cookie
		addSession("user", this.user);
		if (flag) {
//			super.addSession("isMobile", this.clientType);
//			return "successMobile";
		}
		return SUCCESS;
	}

	public void logout() {
		super.clearSession();
	}
}