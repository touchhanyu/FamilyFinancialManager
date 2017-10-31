package com.ffm.stock.input;

import java.util.List;
import java.util.Properties;

import com.ffm.common.db.SQLUtil;
import com.ffm.common.util.PropertiesUtil;
import com.ffm.common.util.RequestUtil;

public class StockInfo4Tencent {
	private final static String URL;
	static {
		Properties prop = PropertiesUtil.readProperties("/com/ffm/stock/input/res/stock.properties");
		URL = prop.getProperty("tencentApi");
	}

	public void updateStockInfoDaily() {
		String sql = "select GTYPE||GID from FFM_STOCK_COMPANY order by id";
		List<Object[]> list = SQLUtil.query(sql);
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = list.get(i);
			String gid = (String) objs[0];
			String url = URL + gid;
			RequestUtil.requestString(url, RequestUtil.GET);
		}
	}

	/**
	 * 根据返回结果，解析股票数据
	 * @param json
	 * @return
	 */
	public Object parseStockData4Tencent(String json) {
		String[] split = json.split(";");
		for (int i = 0; i < split.length; i++) {
			String data = split[i];
			String[] datas = data.split("=");
			if (datas.length == 2) {
				datas[0].replaceAll("v_", "").replaceAll("sh", "").replaceAll("sz", "");
				String[] stockInfo = datas[1].replaceAll("\"", "").split("~");
				System.out.println(stockInfo.length);
			}
		}
		return null;
	}
}