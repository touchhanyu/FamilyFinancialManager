package com.ffm.currency.test;

import java.sql.Connection;

import org.junit.Test;

import com.ffm.common.db.JDBCUtil;
import com.ffm.currency.input.ExchangeRate;
import com.ffm.currency.service.CurrencyService;
import com.ffm.currency.service.impl.CurrencyServiceImpl;

public class MyTest {

	@Test
	public void testJDBC() {
		Connection conn = JDBCUtil.getConn();
		System.out.println(conn);
	}

	@Test
	public void testEntitySQL() {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.requestRMBCurrency();
//		DAOUtil.parseEntity2SQL(Currency.class);
	}

	@Test
	public void test() {
		CurrencyService service = new CurrencyServiceImpl();
		service.requestData();
	}
}