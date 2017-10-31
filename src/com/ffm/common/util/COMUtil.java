package com.ffm.common.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class COMUtil {
	/**
	 * 获取项目路径
	 * 
	 * @return
	 */
	public static String sysPath() {
		URL url = COMUtil.class.getResource("/");
		@SuppressWarnings("deprecation")
		String dir = URLDecoder.decode(url.getPath());
		File file = new File(dir);
		dir = file.getParentFile().getParentFile().getPath() + File.separator;
		return dir;
	}

	@SuppressWarnings("deprecation")
	public static String classPath() {
		URL url = COMUtil.class.getResource("/");
		return URLDecoder.decode(url.getPath());
	}

	/**
	 * 根据给定字符补位
	 * 
	 * @param num
	 * @param len 总长度
	 * @param c 补位的字符
	 * @return
	 */
	public static String fillStr(int num, int len, String c) {
		String s = num + "";
		if (s.length() < len) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < len - s.length(); i++) {
				sb.append(c);
			}
			sb.append(s);
			s = sb.toString();
		}
		return s;
	}

	public static String currentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String currentTime() {
		Date date = new Date();
		@SuppressWarnings("deprecation")
		String time = date.toLocaleString();
		return time.substring(time.length() - 8, time.length());
	}

	/**
	 * 生成文件路径文件夹
	 * 
	 * @param path
	 */
	public static void mkDir(String path) {
		int indexOf = path.lastIndexOf("/");
		if (indexOf == -1) {
			indexOf = path.lastIndexOf("\\");
		}
		String filePath = path.substring(0, indexOf);
		File file = new File(filePath);
		if (!file.exists())
			file.mkdirs();
	}

	/**
	 * 统一编码格式
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		String charset = "UTF-8";
		String encode = null;
		try {
			encode = URLEncoder.encode(str, charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			encode = str;
			e.printStackTrace();
		}
		return encode;
	}
}