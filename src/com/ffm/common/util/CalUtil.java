package com.ffm.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CalUtil {
	public static final int SCALE = 10;
	public static final String FORMAT_NORMAL = "#.##";
	public static final String FORMAT_CURRENCY = "#,##0.00";

	/**
	 * 两数相加
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal add(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(d1 + "");
		BigDecimal bd2 = new BigDecimal(d2 + "");
		return bd1.add(bd2);
	}

	/**
	 * 两数相减
	 * 
	 * @param d1 被减数
	 * @param d2 减数
	 * @return
	 */
	public static double sub(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(d1 + "");
		BigDecimal bd2 = new BigDecimal(d2 + "");
		return bd1.subtract(bd2).doubleValue();
	}

	/**
	 * 两数相乘
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal mul(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(d1 + "");
		BigDecimal bd2 = new BigDecimal(d2 + "");
		return bd1.multiply(bd2);
	}

	/**
	 * 两数相乘
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal mul(BigDecimal d1, BigDecimal d2) {
		BigDecimal bd1 = new BigDecimal(d1 + "");
		BigDecimal bd2 = new BigDecimal(d2 + "");
		return bd1.multiply(bd2);
	}

	/**
	 * 两数相除
	 * 
	 * @param d1 被除数
	 * @param d2 除数
	 * @return
	 */
	public static double div(double d1, double d2) {
		return CalUtil.div(d1, d2, CalUtil.SCALE);
	}

	/**
	 * 两数相除
	 * 
	 * @param d1 被除数
	 * @param d2 除数
	 * @param scale 小数点后位数
	 * @return
	 */
	public static double div(double d1, double d2, int scale) {
		BigDecimal bd1 = new BigDecimal(d1 + "");
		BigDecimal bd2 = new BigDecimal(d2 + "");
		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double round(double d, int scale) {
		BigDecimal bd = new BigDecimal(d + "");
		bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	public static String round(double d, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(d);
	}

	public static void main(String[] args) {
		double add = CalUtil.div(10, 3);
		System.out.println(add);
	}
}