package com.ffm.stock.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ffm.stock.service.StockService;
import com.ffm.stock.service.impl.StockServiceImpl;
import com.ffm.sys.entity.SysUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/ffm/common/spring/applicationContext.xml" })
public class MyTest {
	@Autowired
	private StockService impl;

	@Test
	public void testService1() {
		SysUser user = new SysUser();
		user.setId(2);
		this.impl = new StockServiceImpl();
		this.impl.calculateStockCost("sh600698", user);
	}
}