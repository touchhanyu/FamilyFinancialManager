package com.ffm.sys.action;

import java.io.IOException;
import java.util.List;

import com.ffm.common.action.BaseAction;
import com.ffm.common.entity.DataGrid;
import com.ffm.common.util.JSONUtil;
import com.ffm.sys.entity.Sys_SysTask;
import com.ffm.sys.service.SysTaskService;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class SysTaskAction extends BaseAction {
	private Sys_SysTask sysTask;
	private int rows;
	private int page;
	private String insData;
	private String updData;
	private String inserted;

	public Sys_SysTask getSysTask() {
		return sysTask;
	}

	public void setSysTask(Sys_SysTask sysTask) {
		this.sysTask = sysTask;
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

	public String getInsData() {
		return insData;
	}

	public void setInsData(String insData) {
		this.insData = insData;
	}

	public String getUpdData() {
		return updData;
	}

	public void setUpdData(String updData) {
		this.updData = updData;
	}

	public String getInserted() {
		return inserted;
	}

	public void setInserted(String inserted) {
		this.inserted = inserted;
	}

	private SysTaskService service;

	public void setService(SysTaskService service) {
		this.service = service;
	}

	public void querySysTask() throws IOException {
		DataGrid grid = this.service.querySysTaskByPage(this.page, this.rows);
		Gson gson = new Gson();
		String json = gson.toJson(grid);
		super.outputJSON(json);
	}

	public String saveSysTask() {
		List<Sys_SysTask> insTasks = JSONUtil.parseJSON(this.insData, Sys_SysTask.class);
		List<Sys_SysTask> updTasks = JSONUtil.parseJSON(this.updData, Sys_SysTask.class);
		if (insTasks != null)
			this.service.saveSysTask(insTasks);
		if (updTasks != null)
			this.service.modifySysTask(updTasks);
		if (this.sysTask != null)
			this.service.removeSysTask(this.sysTask);
		return SUCCESS;
	}

	public String runTask() {
		this.service.runSysTask(this.sysTask);
		return SUCCESS;
	}
}