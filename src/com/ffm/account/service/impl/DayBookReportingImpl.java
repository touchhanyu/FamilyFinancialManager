package com.ffm.account.service.impl;

import java.util.List;

import com.ffm.account.service.DayBookReporting;
import com.ffm.common.db.SQLUtil;
import com.ffm.common.entity.DictionaryDetail;
import com.ffm.common.entity.PropertyGridRow;
import com.ffm.common.util.COMUtil;
import com.ffm.jfreechart.BarChart;
import com.ffm.jfreechart.ChartData;
import com.ffm.sys.entity.SysUser;
import com.google.gson.Gson;

public class DayBookReportingImpl implements DayBookReporting {

	@Override
	public String queryFinancialDetail(String month, SysUser user) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("select TYPE,DIRECTION,dict1.DICTVALUE \"GROUP\",dict2.DICTVALUE NAME,TOTAL from ");
		sb.append("(select TYPE,DIRECTION,sum(AMOUNT)TOTAL from FFM_ACCOUNT_DAYBOOK where TRANOPER=");
		sb.append(user.getId()).append(" and to_char(TRANDATE,'YYYY-MM')='");
		sb.append(month).append("' group by DIRECTION,TYPE)daybook left join ");
		sb.append("(select DICTCODE,DICTVALUE from FFM_SYS_DICTIONARYDETAIL where DID=(select ID from FFM_SYS_DICTIONARY where DICTCODE='moneytype') and STATE='1')dict1");
		sb.append(" on daybook.TYPE=dict1.DICTCODE left join ");
		sb.append("(select DICTCODE,DICTVALUE from FFM_SYS_DICTIONARYDETAIL where DID=(select ID from FFM_SYS_DICTIONARY where DICTCODE='moneydirection') and STATE='1')dict2");
		sb.append(" on daybook.DIRECTION=dict2.DICTCODE order by TYPE,DIRECTION");
		List<PropertyGridRow> list = SQLUtil.query(sb.toString(), PropertyGridRow.class);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	@Override
	public String makeMonthlyReporting(String month, SysUser user) {
		// TODO Auto-generated method stub
		String sql = "select (select DICTVALUE from FFM_SYS_DICTIONARYDETAIL where DID=(select ID from FFM_SYS_DICTIONARY where DICTCODE='moneydirection') and DICTCODE=DIRECTION)TYPE,TYPE ACCOUNT,AMOUNT VALUE,((to_char(TRANDATE,'dd')*1)||'')NAME from FFM_ACCOUNT_DAYBOOK where TRANOPER="
				+ user.getId() + " and to_char(TRANDATE,'YYYY-MM')='" + month + "' order by TRANDATE";
		List<ChartData> data = SQLUtil.query(sql, ChartData.class);
		if (data.size() == 0)
			return "";
		BarChart barChart = new BarChart("个人月度支入支出", "", "", data);
		String path = "\\File\\account\\" + user.getName() + "_" + month + ".jpeg";
		barChart.makeBarChart(COMUtil.sysPath() + path);
		return path;
	}

	@Override
	public String daybookReportingMonth(SysUser user) {
		// TODO Auto-generated method stub
		String sql = "select YEAR DICTCODE,YEAR DICTVALUE from (select distinct to_char(TRANDATE,'yyyy')YEAR from ffm_account_daybook where TRANOPER=" + user.getId() + ")";
		List<DictionaryDetail> list = SQLUtil.query(sql, DictionaryDetail.class);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
}