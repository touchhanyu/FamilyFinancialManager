package com.ffm.stock.task;

import java.sql.SQLException;

import com.ffm.common.task.SysTask;
import com.ffm.common.util.SysUtil;
import com.ffm.stock.input.StockInfo4Sina;

public class StockInfoTask extends SysTask {

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		StockInfo4Sina sina = SysUtil.getInstance(StockInfo4Sina.class);
		try {
			sina.updateStockInfoDaliy();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}