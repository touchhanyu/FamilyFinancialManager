package com.ffm.stock.test;

import java.sql.SQLException;

import org.junit.Test;

import com.ffm.common.util.RequestUtil;
import com.ffm.stock.input.StockInfo;
import com.ffm.stock.input.StockInfo4Sina;
import com.ffm.stock.input.StockInfo4Tencent;

public class StockTest {
	@Test
	public void testCompany() {
	}

	@Test
	public void testStock() {
		StockInfo stockInfo = new StockInfo();
		stockInfo.updateStockDailyByAPI();
	}

	@Test
	public void testS() throws SQLException {
		StockInfo4Sina sina = new StockInfo4Sina();
		sina.updateStockInfoDaliy();
	}

	@Test
	public void testT() {
		String url = "http://qt.gtimg.cn/q=sz000858,sh600000";
		String json = RequestUtil.requestString(url, "GET", null, "GBK");
		System.out.println(json);
		StockInfo4Tencent tencent = new StockInfo4Tencent();
		tencent.parseStockData4Tencent(json);
	}
}