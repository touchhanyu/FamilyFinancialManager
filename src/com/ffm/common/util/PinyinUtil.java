package com.ffm.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	/**
	 * 中文转换拼音
	 * 
	 * @param str
	 * @return
	 */
	public static String translateChinese(String str) {
		HanyuPinyinOutputFormat pinyinFormat = new HanyuPinyinOutputFormat();
		pinyinFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pinyinFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		String pinyin = "";
		try {
			char[] chinese = str.toCharArray();
			for (int i = 0; i < chinese.length; i++) {
				char c = chinese[i];
				if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
					pinyin += c;
				}
				String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, pinyinFormat);
				if (pinyinArray != null && pinyinArray.length > 0) {
					pinyin += pinyinArray[0];
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pinyin;
	}
}