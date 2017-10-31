package com.ffm.sys.service;

import java.util.List;

import com.ffm.common.entity.DataGrid;
import com.ffm.sys.entity.Sys_SysTask;

public interface SysTaskService {
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	DataGrid querySysTaskByPage(int page, int size);

	void saveSysTask(List<Sys_SysTask> sysTasks);

	void modifySysTask(List<Sys_SysTask> sysTasks);

	void removeSysTask(Sys_SysTask sysTask);

	void runSysTask(Sys_SysTask sysTask);
}