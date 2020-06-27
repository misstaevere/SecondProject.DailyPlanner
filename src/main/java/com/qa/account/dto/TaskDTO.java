package com.qa.account.dto;

import java.sql.Date;
import java.sql.Time;

public class TaskDTO { // data transfer obj, it's the data format you want for your front end
	
	private long taskId;
	private String taskName;
	private Date taskDate;
	private Time taskTime;
	private String taskLocation;
	
	public TaskDTO() {
		super();
	}

	public TaskDTO(long taskId, String taskName, Date taskDate, Time taskTime, String taskLocation) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDate = taskDate;
		this.taskTime = taskTime;
		this.taskLocation = taskLocation;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	public Time getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Time taskTime) {
		this.taskTime = taskTime;
	}

	public String getTaskLocation() {
		return taskLocation;
	}

	public void setTaskLocation(String taskLocation) {
		this.taskLocation = taskLocation;
	}
	
}
