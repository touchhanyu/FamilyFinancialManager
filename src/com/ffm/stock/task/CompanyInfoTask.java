package com.ffm.stock.task;

import com.ffm.common.db.SQLUtil;
import com.ffm.common.task.SysTask;
import com.ffm.stock.input.CompanyInfo;

public class CompanyInfoTask extends SysTask {

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		CompanyInfo companyInfo = new CompanyInfo();
		String sql = "delete FFM_STOCK_STOCK";
		SQLUtil.update(sql, null);
		companyInfo.resetCount();
		companyInfo.updateCompany(0);
		companyInfo.updateCompany(1);
	}
}