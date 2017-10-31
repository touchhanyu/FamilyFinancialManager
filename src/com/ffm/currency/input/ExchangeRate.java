package com.ffm.currency.input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ffm.common.util.PropertiesUtil;
import com.ffm.common.util.RequestUtil;
import com.ffm.currency.entity.Currency;
import com.ffm.currency.entity.Result;

public class ExchangeRate {

	/* 人民币请求地址 */
	public static final String RMB_URL;
	/* 外汇请求地址 */
	public static final String FRATE_URL;
	/* 申请的APPKEY */
	public static final String APPKEY;
	/* 请求结果编码格式 */
	public static final String CHATSET = "UTF-8";
	private Logger logger = Logger.getLogger(ExchangeRate.class);

	/**
	 * 读取配置
	 */
	static {
		Properties prop = PropertiesUtil.readProperties("/com/ffm/currency/input/res/currency.properties");
		if (prop == null)
			throw new ExceptionInInitializerError("=====读取配置文件失败!!!======");
		RMB_URL = prop.getProperty("rmb");
		FRATE_URL = prop.getProperty("frate");
		APPKEY = prop.getProperty("appkey");
	}

	public Result requestRMBCurrency() {
		/** type 0或者1,默认为0 **/
		/** bank 0:工商银行,1:招商银行,2:建设银行,3:中国银行,4:交通银行,5:农业银行,默认为:中国银行 **/
		String json = RequestUtil.requestString(RMB_URL + "?key=" + APPKEY, "GET");
		this.logger.debug(json);
		return this.packageEntity(json);
	}

	/**
	 * 组装实体类
	 * @param json
	 */
	@SuppressWarnings("unchecked")
	public Result packageEntity(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		String resultcode = jsonObject.getString("resultcode");
		String reason = jsonObject.getString("reason");
		String error_code = jsonObject.getString("error_code");
		Result res = new Result();
		res.setResultcode(resultcode);
		res.setReason(reason);
		res.setError_code(error_code);
		JSONArray currencies = jsonObject.getJSONArray("result");
		if (currencies.size() == 0)
			return null;// 没有请求到数据
		Map<String, Object> result = (Map<String, Object>) currencies.get(0);
		Set<Entry<String, Object>> set = result.entrySet();
		Iterator<Entry<String, Object>> it = set.iterator();
		ArrayList<Currency> list = new ArrayList<Currency>();
		res.setResult(list);
		int id = 1;
		while (it.hasNext()) {
			Entry<String, Object> next = it.next();
			Map<String, String> data = (Map<String, String>) next.getValue();
			String name = data.get("name");
			String fBuyPri = data.get("fBuyPri");
			String mBuyPri = data.get("mBuyPri");
			String fSellPri = data.get("fSellPri");
			String mSellPri = data.get("mSellPri");
			String bankConversionPri = data.get("bankConversionPri");
			String date = data.get("date");
			String time = data.get("time");
			Currency currency = new Currency();
			currency.setId(id++);
			currency.setName(name);
			currency.setfBuyPri(fBuyPri);
			currency.setmBuyPri(mBuyPri);
			currency.setfSellPri(fSellPri);
			currency.setmSellPri(mSellPri);
			currency.setBankConversionPri(bankConversionPri);
			currency.setDate(date);
			currency.setTime(time);
			list.add(currency);
		}
		return res;
	}

	public Map<String, String> checkNull(Map<String, String> data) {
		return data;
	}
}