package com.ffm.common.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ffm.common.util.SysUtil;
import com.ffm.sys.entity.SysUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	public ValueStack getValueStack() {
		ActionContext context = ActionContext.getContext();
		return context.getValueStack();
	}

	public SysUser getSysUser() {
		Object object = this.getSessionAttributes("user");
		if (object == null)
			return null;
		return (SysUser) object;
	}

	public Object getSessionAttributes(String name) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		return session.getAttribute(name);
	}

	public void addSession(String key, Object obj) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute(key, obj);
	}

	public void clearSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		SysUtil.addCookie(response, 0, null, null);
	}

	public Object getValueOGNL(String reg) {
		return this.getValueStack().findValue(reg);
	}

	public void setValueOGNL(String attr, Object obj) {
		ValueStack valueStack = this.getValueStack();
		valueStack.set(attr, obj);
	}

	/**
	 * 调用response输出JSON
	 * @param json
	 * @throws IOException
	 */
	protected void outputJSON(String json) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(json);
	}
}