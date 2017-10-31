package com.ffm.common.intercepter;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.ffm.common.db.SQLUtil;
import com.ffm.common.util.SysUtil;
import com.ffm.sys.entity.SysUser;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class AuthorityInterceptor implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		/** 检查cookie **/
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		String[] data = SysUtil.checkCookies(cookies);
		ActionContext actionContext = invocation.getInvocationContext();
		Map<String, Object> session = actionContext.getSession();
		Object user = session.get("user");
		if (user == null) {
			if (data[0] == null || data[1] == null) {// cookies,session都为空
				return Action.LOGIN;
			} else {
				String name = URLDecoder.decode(data[0], "UTF-8");
				SysUtil.addCookie(ServletActionContext.getResponse(), name, data[1]);
				String sql = "select ID,NAME,PASSWORD,AUTHORITY from FFM_SYS_USER where NAME='" + name + "'";
				List<SysUser> list = SQLUtil.query(sql, SysUser.class);
				if (list.size() == 1) {
					SysUser sysUser = list.get(0);
					session.put("user", sysUser);
				}
			}
		}
		return invocation.invoke();
	}
}