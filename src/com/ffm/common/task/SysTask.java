package com.ffm.common.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ffm.common.db.SQLUtil;
import com.ffm.common.util.COMUtil;
import com.ffm.sys.entity.Sys_SysTask;

public abstract class SysTask {
	/* 任务配置参数 */
	private Sys_SysTask sysTask;
	private long oneDay = 24 * 60 * 60 * 1000;

	public Sys_SysTask getSysTask() {
		return sysTask;
	}

	public void setSysTask(Sys_SysTask sysTask) {
		this.sysTask = sysTask;
	}

	/**
	 * 定时任务回调函数
	 */
	public abstract void doTask();

	public void execute() {
		if (this.sysTask.getTaskTimes() == 1 && this.sysTask.getTaskStartTime() == null) {// 立即执行
			doTask();
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = COMUtil.currentDate();
		try {
			Date date = sdf.parse(currentDate + " " + this.sysTask.getTaskStartTime());
			Date nowTime = new Date();
			long initDelay = date.getTime() - nowTime.getTime();// 延迟时间
			if (initDelay < 0) {// 已过设定开始时间，下一日执行
				initDelay = this.oneDay + initDelay;
			}
			ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					String sql = "update FFM_SYS_SYSTASK set TASKSTATUS='2' where TASKID=" + sysTask.getTaskId();// 更新系统任务表状态为执行中
					SQLUtil.update(sql, null);
					try {
						doTask();
						sql = "update FFM_SYS_SYSTASK set TASKSTATUS='1' where TASKID=" + sysTask.getTaskId();// 更新系统任务表状态为执行中
						SQLUtil.update(sql, null);
					} catch (Exception e) {
						// TODO: handle exception
						sql = "update FFM_SYS_SYSTASK set TASKSTATUS='9' where TASKID=" + sysTask.getTaskId();// 更新系统任务表状态为失败
						SQLUtil.update(sql, null);
						e.printStackTrace();
					}
				}
			};
			service.scheduleAtFixedRate(runnable, initDelay, this.oneDay, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}