package com.ffm.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static Properties readProperties(String path) {
		InputStream is = null;
		Properties prop = null;
		try {
			is = PropertiesUtil.class.getResourceAsStream(path);
			prop = new Properties();
			prop.load(is);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}