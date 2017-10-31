package com.ffm.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class RequestUtil {
	public static final String GET = "GET";
	public static final String POST = "POST";
	private static final String CHARSET = "UTF-8";

	/**
	 * 请求http资源
	 * @param strUrl 请求http地址
	 * @param method GET/POST
	 * @return 返回String类型
	 */
	public static String requestString(String strUrl, String method) {
		return requestString(strUrl, method, null, CHARSET);
	}

	/**
	 * 请求http资源
	 * @param strUrl 请求http地址
	 * @param method GET/POST
	 * @param charSet 编码格式
	 * @return 返回String类型
	 */
	public static String requestString(String strUrl, String method, String charSet) {
		return requestString(strUrl, method, null, charSet);
	}

	/**
	 * 请求http资源
	 * @param strUrl 请求http地址
	 * @param method GET/POST
	 * @param data 请求附加参数
	 * @return 返回String类型
	 */
	public static String requestString(String strUrl, String method, Map<String, String> data) {
		return requestString(strUrl, method, data, CHARSET);
	}

	/**
	 * 请求http资源
	 * 
	 * @param strUrl 请求发送的地址
	 * @param method 请求方法
	 * @param data POST方式请求的参数
	 * @param charSet 返回结果的编码格式
	 * @return 请求结果
	 */
	public static String requestString(String strUrl, String method, Map<String, String> data, String charSet) {
		HttpURLConnection conn = null;
		BufferedReader br = null;
		String str = null;
		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || "GET".equals(method)) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				Set<Entry<String, String>> entrySet = data.entrySet();
				Iterator<Entry<String, String>> it = entrySet.iterator();
				while (it.hasNext()) {
					Entry<String, String> entry = it.next();
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
				conn.setDoOutput(true);
			}
			conn.setDefaultUseCaches(false);
			conn.connect();
			InputStream is = conn.getInputStream();
			str = IOUtil.parseString(is, charSet);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				conn.disconnect();
		}
		return str;
	}

	/**
	 * 请求http资源
	 * @param strUrl 请求发送的地址
	 * @param method 请求方法
	 * @param outPath 本地路径
	 * @param fileName 保存到本地的文件名
	 */
	public static void requestImg(String strUrl, String method, String outPath, String fileName) {
		requestImg(strUrl, method, null, outPath, fileName);
	}

	/**
	 * 
	 * @param strUrl 请求发送的地址
	 * @param method 请求方法
	 * @param data POST方式请求的参数
	 * @param outPath 本地路径
	 * @param fileName 保存到本地的文件名
	 */
	public static void requestImg(String strUrl, String method, Map<String, String> data, String outPath, String fileName) {
		HttpURLConnection conn = request(strUrl, method, data, CHARSET);
		OutputStream os = null;
		try {
			if (conn == null)
				return;
			InputStream is = conn.getInputStream();
			os = new FileOutputStream(outPath + File.separator + fileName);
			byte[] b = new byte[1024];
			int len = 0;
			while (true) {
				len = is.read(b);
				if (len == -1)
					break;
				os.write(b, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.disconnect();
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 请求http资源
	 * 
	 * @param strUrl 请求发送的地址
	 * @param method 请求方法
	 * @param data POST方式请求的参数
	 * @param charSet 返回结果的编码格式
	 * @return 请求到的网络资源
	 */
	public static HttpURLConnection request(String strUrl, String method, Map<String, String> data, String charSet) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || "GET".equals(method)) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				Set<Entry<String, String>> entrySet = data.entrySet();
				Iterator<Entry<String, String>> it = entrySet.iterator();
				while (it.hasNext()) {
					Entry<String, String> entry = it.next();
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
				conn.setDoOutput(true);
			}
			conn.setDefaultUseCaches(false);
			conn.connect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}