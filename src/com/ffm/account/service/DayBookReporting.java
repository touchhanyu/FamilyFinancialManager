package com.ffm.account.service;

import com.ffm.sys.entity.SysUser;

public interface DayBookReporting {
	/**
	 * 个人账务月度信息汇总
	 * 
	 * @param month 汇总月份
	 * @param user 用户
	 * @return
	 */
	String queryFinancialDetail(String month, SysUser user);

	String makeMonthlyReporting(String month, SysUser uer);

	String daybookReportingMonth(SysUser user);
}