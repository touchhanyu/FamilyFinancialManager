package com.ffm.sys.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ffm.common.action.BaseAction;
import com.ffm.common.db.SQLUtil;
import com.ffm.common.entity.DictionaryDetail;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class CommAction extends BaseAction {
	private String q;
	private int rows;
	private int page;
	private String param;

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

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * 单一数据字典查询
	 * 
	 * @throws IOException
	 */
	public void dictQuery() throws IOException {
		String sql = "select ID,DICTCODE,DICTVALUE,DICTVALUE TEXT from FFM_SYS_DICTIONARYDETAIL where DID=(select ID from FFM_SYS_DICTIONARY where DICTCODE='"
				+ this.param + "') order by DORDER";
		List<DictionaryDetail> list = SQLUtil.query(sql, DictionaryDetail.class);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
	}

	public void dictQueryByPID() throws IOException {
		String sql = "select ID,DID,PID,DICTCODE,DICTVALUE from FFM_SYS_DICTIONARYDETAIL where PID=" + this.param + " order by DORDER";
		List<DictionaryDetail> list = SQLUtil.query(sql, DictionaryDetail.class);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
	}
}