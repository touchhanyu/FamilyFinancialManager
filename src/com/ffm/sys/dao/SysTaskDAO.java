package com.ffm.sys.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class SysTaskDAO extends DAO {
	public SysTaskDAO() {
		super.insSqlTemp = "insert into FFM_SYS_SYSTASK(TASKID,TASKNAME,TASKSTARTTIME,TASKTIMES,TASKPER,TASKPATH,TASKSTATUS) values(taskId?,taskName?,taskStartTime?,taskTimes?,taskPer?,taskPath?,taskStatus?)";
		super.updSqlTemp = "update FFM_SYS_SYSTASK set TASKNAME=taskName?,TASKSTARTTIME=taskStartTime?,TASKTIMES=taskTimes?,TASKPER=taskPer?,TASKPATH=taskPath?,TASKSTATUS=taskStatus? where TASKID=taskId?";
		super.delSqlTemp = "delete FFM_SYS_SYSTASK where TASKID=taskId?";
		super.insSql = "insert into FFM_SYS_SYSTASK(TASKID,TASKNAME,TASKSTARTTIME,TASKTIMES,TASKPER,TASKPATH,TASKSTATUS) values(?,?,?,?,?,?,?)";
		super.updSql = "update FFM_SYS_SYSTASK set TASKNAME=?,TASKSTARTTIME=?,TASKTIMES=?,TASKPER=?,TASKPATH=?,TASKSTATUS=? where TASKID=?";
		super.delSql = "delete FFM_SYS_SYSTASK where TASKID=?";
	}

	@Override
	public <Sys_SysTask> void insert(Sys_SysTask sysTask) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.insert(this, sysTask);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <Sys_SysTask> void update(Sys_SysTask sysTask) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.update(this, sysTask);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <Sys_SysTask> void delete(Sys_SysTask sysTask) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.delete(this, sysTask);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <Sys_SysTask> void insert(List<Sys_SysTask> list) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.insert(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <Sys_SysTask> void update(List<Sys_SysTask> list) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.update(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <Sys_SysTask> void delete(List<Sys_SysTask> list) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.delete(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}