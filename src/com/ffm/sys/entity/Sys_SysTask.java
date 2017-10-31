package com.ffm.sys.entity;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

@Table("FFM_SYS_SYSTASK")
public class Sys_SysTask {
	@Column("TASKID")
	/* 任务编号 */
	private int taskId;
	@Column("TASKNAME")
	/* 任务名称 */
	private String taskName;
	@Column("TASKSTARTTIME")
	/* 任务开始时间 */
	private String taskStartTime;
	@Column("TASKTIMES")
	/* 任务执行次数 */
	private int taskTimes;
	@Column("TASKPER")
	/* 任务间隔时间 */
	private int taskPer;
	@Column("TASKPATH")
	/* 任务类全路径 */
	private String taskPath;
	@Column("TASKSTATUS")
	/* 任务状态 */
	private String taskStatus;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public int getTaskTimes() {
		return taskTimes;
	}

	public void setTaskTimes(int taskTimes) {
		this.taskTimes = taskTimes;
	}

	public int getTaskPer() {
		return taskPer;
	}

	public void setTaskPer(int taskPer) {
		this.taskPer = taskPer;
	}

	public String getTaskPath() {
		return taskPath;
	}

	public void setTaskPath(String taskPath) {
		this.taskPath = taskPath;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
}