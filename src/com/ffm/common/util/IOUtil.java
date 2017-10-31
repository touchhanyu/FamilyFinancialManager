package com.ffm.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtil {
	private static final String CHARSET = "UTF-8";

	public static String parseString(InputStream is) {
		return parseString(is, CHARSET);
	}

	public static String parseString(InputStream is, String charSet) {
		StringBuffer sb = new StringBuffer();
		try {
			InputStreamReader isr = new InputStreamReader(is, charSet);
			BufferedReader br = new BufferedReader(isr);
			while (true) {
				String str = br.readLine();
				if (str == null)
					break;
				sb.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}