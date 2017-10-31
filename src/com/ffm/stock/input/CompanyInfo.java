package com.ffm.stock.input;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ffm.common.util.COMUtil;
import com.ffm.common.util.PinyinUtil;
import com.ffm.common.util.PropertiesUtil;
import com.ffm.common.util.RequestUtil;
import com.ffm.stock.dao.CompanyDAO;
import com.ffm.stock.entity.Company;

public class CompanyInfo {
	/* 上证股市 */
	private static final String SHURL;
	/* 深证股市 */
	private static final String SZURL;
	/* APP KEY */
	private static final String APPKEY;
	private static int count = 1;
	private Logger logger = Logger.getLogger(CompanyInfo.class);
	static {
		Properties prop = PropertiesUtil.readProperties("/com/ffm/stock/input/res/stock.properties");
		SHURL = prop.getProperty("shCompanyUrl");
		SZURL = prop.getProperty("szCompanyUrl");
		APPKEY = prop.getProperty("appKey");
	}

	public void updateCompany(int type) {
		int i = 1, totalPage = 1;
		ArrayList<Company> companies = new ArrayList<Company>();
		String baseUrl = "", gtype = "";
		if (type == 0) {
			baseUrl = SHURL;
			gtype = "sh";
		} else if (type == 1) {
			baseUrl = SZURL;
			gtype = "sz";
		}
		while (true) {
			if ("".equals(baseUrl) || "".equals(gtype)) {
				this.logger.error("请求类型错误！");
				break;
			}
			String url = baseUrl + "?key=" + APPKEY + "&type=4&page=" + i++;
			String json = RequestUtil.requestString(url, "GET");// 请求网络数据
			JSONObject jsonObject = JSONObject.fromObject(json);
			String string = jsonObject.getString("result");
			if ("[]".equals(string))
				break;// 返回数据为空
			JSONObject result = jsonObject.getJSONObject("result");
			String num = result.getString("num");
			totalPage = Integer.parseInt(num);// 总页数
			JSONArray datas = (JSONArray) result.get("data");
			Date date = new Date();
			String time = COMUtil.currentTime();
			for (int j = 0; j < datas.size(); j++) {
				@SuppressWarnings("unchecked")
				Map<String, Object> data = (Map<String, Object>) datas.get(j);
				String symbol = data.get("symbol").toString().replace(gtype, "");
				String gname = data.get("name").toString();
				String pinyin = PinyinUtil.translateChinese(gname);
				Company company = new Company();
				company.setId(CompanyInfo.count++);
				company.setPinyin(pinyin);
				company.setGid(symbol);
				company.setGname(gname);
				company.setGtype(gtype);
				company.setMakeDate(date);
				company.setMakeTime(time);
				companies.add(company);
			}
			if (i == totalPage)
				break;// 最后一页
		}
		this.logger.info("request end");
		CompanyDAO dao = new CompanyDAO();
		dao.insert(companies);
		this.logger.info(gtype + " type update completed!");
	}

	/**
	 * 重置主键
	 */
	public void resetCount() {
		CompanyInfo.count = 1;
	}
}