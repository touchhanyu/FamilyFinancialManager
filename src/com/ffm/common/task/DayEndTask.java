package com.ffm.common.task;

import com.ffm.common.db.SQLUtil;

public class DayEndTask extends SysTask {

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		String sql = "update FFM_SYS_SYSVAR set VALUE=to_char(to_date(VALUE,'yyyyMMdd')+1,'yyyyMMdd') where VARTYPE='sysdate'";// 日切
		SQLUtil.update(sql, null);
	}
}