package com.ffm.account.action;

import java.io.IOException;

import com.ffm.account.entity.DayBook;
import com.ffm.account.service.DayBookReporting;
import com.ffm.account.service.DayBookService;
import com.ffm.common.action.BaseAction;
import com.ffm.common.entity.DataGrid;
import com.ffm.sys.entity.SysUser;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class DaybookAction extends BaseAction {
	private String q;
	private int rows;
	private int page;
	private DayBook dayBook;
	private String gdirection;
	private String date1;
	private String date2;
	private String year;
	private String month;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public DayBook getDayBook() {
		return dayBook;
	}

	public void setDayBook(DayBook dayBook) {
		this.dayBook = dayBook;
	}

	public String getGdirection() {
		return gdirection;
	}

	public void setGdirection(String gdirection) {
		this.gdirection = gdirection;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	private DayBookService service;
	private DayBookReporting daybookReporting;

	public void setService(DayBookService service) {
		this.service = service;
	}

	public void setDaybookReporting(DayBookReporting daybookReporting) {
		this.daybookReporting = daybookReporting;
	}

	public String saveDaybook() {
		try {
			this.dayBook.setTranOper(super.getSysUser().getId());
			this.service.save(this.dayBook);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public String modifyDaybook() {
		try {
			this.dayBook.setTranOper(super.getSysUser().getId());
			this.service.modify(this.dayBook);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public String deleteDaybook() {
		try {
			this.dayBook.setTranOper(super.getSysUser().getId());
			DayBook dayBook = this.service.queryById(this.dayBook.getId());
			this.service.remove(dayBook);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public void daybookList() throws IOException {
		SysUser user = (SysUser) super.getSessionAttributes("user");
		StringBuffer sb = new StringBuffer();
		sb.append("select ID,DIRECTION,TYPE,AMOUNT,PURPOSE,REMARK,TRANDATE,TRANTIME,TRANOPER,");
		sb.append("(select DICTVALUE from FFM_SYS_DICTIONARYDETAIL where DID=(select ID from FFM_SYS_DICTIONARY where DICTCODE='moneydirection') and DICTCODE=DIRECTION)DIRECTIONVALUE,");
		sb.append("(select DICTVALUE from FFM_SYS_DICTIONARYDETAIL where DID=(select ID from FFM_SYS_DICTIONARY where DICTCODE='moneytype') and DICTCODE=TYPE)TYPEVALUE,");
		sb.append("(select DICTVALUE from FFM_SYS_DICTIONARYDETAIL where DID=(select ID from FFM_SYS_DICTIONARY where DICTCODE='moneypurpose') and DICTCODE=PURPOSE)PURPOSEVALUE");
		sb.append(" from FFM_ACCOUNT_DAYBOOK where 1=1");
		if (this.gdirection != null && !"".equals(this.gdirection)) {
			sb.append(" and DIRECTION='").append(this.gdirection).append("'");
		}
		if (this.date1 != null && !"".equals(this.date1)) {
			sb.append(" and TRANDATE>=to_date('").append(this.date1).append("','yyyy-MM-dd')");
		}
		if (this.date2 != null && !"".equals(this.date2)) {
			sb.append(" and TRANDATE<=to_date('").append(this.date2).append("','yyyy-MM-dd')");
		}
		if (!"0".equals(user.getAuthority())) {// 权限控制
			sb.append(" and TRANOPER='").append(user.getId()).append("'");
		}
		sb.append(" order by TRANDATE desc,DIRECTION,TRANOPER");
		DataGrid grid = this.service.queryByPage(sb.toString(), this.page, this.rows);
		Gson gson = new Gson();
		String json = gson.toJson(grid);
		super.outputJSON(json);
	}

	public void daybookReportingMonth() throws IOException {
		String json = this.daybookReporting.daybookReportingMonth(super.getSysUser());
		super.outputJSON(json);
	}

	public void userMonthlyFinancialDetail() throws IOException {
		String time = this.year + "-" + this.month;
		String json = this.daybookReporting.queryFinancialDetail(time, super.getSysUser());
		super.outputJSON(json);
	}

	public void makeMonthlyBarChart() throws IOException {
		SysUser user = super.getSysUser();
		String time = this.year + "-" + this.month;
		String path = this.daybookReporting.makeMonthlyReporting(time, user);
		super.outputJSON(path);
	}
}