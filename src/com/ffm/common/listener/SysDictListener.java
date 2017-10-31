package com.ffm.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.ffm.common.util.SysUtil;

public class SysDictListener implements ServletContextListener {
	private Logger logger = Logger.getLogger(SysDictListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("开始加载数据字典...");
		SysUtil.initDictionary();
	}
}