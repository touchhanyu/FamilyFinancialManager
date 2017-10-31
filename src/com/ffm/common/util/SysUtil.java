package com.ffm.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SysUtil {
	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 * @param pwd
	 */
	public static void addCookie(HttpServletResponse response, String name, String pwd) {
		int maxAge = 604800;// 一周内免登录60*60*24*7
		addCookie(response, maxAge, name, pwd);
	}

	public static void addCookie(HttpServletResponse response, int maxAge, String name, String pwd) {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		if (name != null)
			name = COMUtil.encode(name);
		Cookie nameCookie = new Cookie("username", name);
		nameCookie.setPath("/");
		nameCookie.setMaxAge(maxAge);
		response.addCookie(nameCookie);
		Cookie ssidCookie = new Cookie("ssid", pwd);
		ssidCookie.setPath("/");
		ssidCookie.setMaxAge(maxAge);
		response.addCookie(ssidCookie);
	}

	/**
	 * 校验Cookie
	 * 
	 * @param cookies
	 * @return
	 */
	public static String[] checkCookies(Cookie[] cookies) {
		String[] data = new String[2];
		String username = null, ssid = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if ("username".equals(cookie.getName())) {
					username = cookie.getValue();
				}
				if ("ssid".equals(cookie.getName())) {
					ssid = cookie.getValue();
				}
			}
		}
		data[0] = username;
		data[1] = ssid;
		return data;
	}

	/**
	 * 由spring工厂创建对象实例
	 * 
	 * @param cls
	 * @return
	 */
	public static <T> T getInstance(Class<T> cls) {
		WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		return ac.getBean(cls);
	}

	/**
	 * 初始化数据字典
	 */
	public static void initDictionary() {
		
	}
}