package com.ffm.wechat.util;

import java.util.Date;
import java.util.Properties;

import net.sf.json.JSONObject;

import com.ffm.common.util.PropertiesUtil;
import com.ffm.common.util.RequestUtil;

public class WechatUtil {
	private static final String TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";
	private static final String MENUURL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional";
	public static final String APPID;
	public static final String TOKEN;
	public static final String AESKEY;
	public static final String APPSECRET;
	private static String access_token;
	private static long nextTime;

	static {
		Properties prop = PropertiesUtil.readProperties("/com/ffm/wechat/cfg.properties");
		APPID = prop.getProperty("APPID");
		TOKEN = prop.getProperty("TOKEN");
		AESKEY = prop.getProperty("AESKEY");
		APPSECRET = prop.getProperty("APPSECRET");
	}

	/**
	 * 获取access_token
	 * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
	 * 
	 * @return
	 */
	public static String requsetAccessToken() {
		Date now = new Date();
		long time = now.getTime();
		if (time > nextTime) {
			String url = TOKENURL + "?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
			String json = RequestUtil.requestString(url, "GET");
			JSONObject jsonObject = JSONObject.fromObject(json);
			access_token = jsonObject.get("access_token").toString();// 获取到的凭证
			String expires_in = jsonObject.get("expires_in").toString();// 凭证有效时间，单位：秒
			long expiresTime = Long.parseLong(expires_in);
			Date date = new Date();
			nextTime = date.getTime() + (expiresTime - 300) * 1000;
		}
		return access_token;
	}

	public static String requestMenu() {
		String access_token = requsetAccessToken();
		String url = MENUURL + "?access_token=" + access_token;
		String json = RequestUtil.requestString(url, "GET");
		return json;
	}
}