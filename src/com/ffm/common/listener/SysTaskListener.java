package com.ffm.common.listener;

import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.ffm.common.db.SQLUtil;
import com.ffm.common.task.SysTask;
import com.ffm.sys.entity.Sys_SysTask;

public class SysTaskListener implements ServletContextListener {
	private Timer timer;
	private Logger logger = Logger.getLogger(SysTaskListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		timer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		this.logger.info("开始加载定时任务...");
		this.timer = new Timer(true);
		String sql = "select TASKID,TASKSTARTTIME,TASKTIMES,TASKPER,TASKPATH,TASKSTATUS from FFM_SYS_SYSTASK where TASKSTATUS<>'0'";// 获取系统任务配置数据
		List<Sys_SysTask> list = SQLUtil.query(sql, Sys_SysTask.class);
		try {
			for (int i = 0; i < list.size(); i++) {
				Sys_SysTask com_SysTask = list.get(i);
				Class<?> clazz = Class.forName(com_SysTask.getTaskPath());
				SysTask task = (SysTask) clazz.newInstance();
				task.setSysTask(com_SysTask);// 传递任务配置信息
				task.execute();// 执行任务方法
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}