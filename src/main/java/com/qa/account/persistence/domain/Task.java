package com.qa.account.persistence.domain;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

// Uses ORM - Object Relational Mapper (persistence in this case) (converts Java objects into tables)
// Jpa - Java Persistance API (converts classes to tables, uses the default const to create the object and then uses the setters to insert values)
@Entity
public class Task {

	@Id // Primary Key
	@GeneratedValue // Auto Increment
	private long taskId;

	@Column(name = "taskName", length = 60) // column is done automatically but makes easier to read and for config
	private String taskName;

	@Column(nullable = true) // CHANGE BACK
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date taskDate;

	@Column(nullable = true) // CHANGE BACK
	private Time taskTime;

	@Column
	private String taskLocation;

	@ManyToOne(targetEntity = User.class)
	private User user;
	
	

	public Task(String taskName, String taskLocation) {
		super();
		this.taskName = taskName;
		this.taskLocation = taskLocation;
	}

	public Task(String taskName, Date taskDate, Time taskTime, String taskLocation) {
		super();
		this.taskName = taskName;
		this.taskDate = taskDate;
		this.taskTime = taskTime;
		this.taskLocation = taskLocation;
	}

	public Task() { // Entities MUST have a default constructor
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskDate == null) ? 0 : taskDate.hashCode());
		result = prime * result + (int) (taskId ^ (taskId >>> 32));
		result = prime * result + ((taskLocation == null) ? 0 : taskLocation.hashCode());
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + ((taskTime == null) ? 0 : taskTime.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskDate == null) {
			if (other.taskDate != null)
				return false;
		} else if (!taskDate.equals(other.taskDate))
			return false;
		if (taskId != other.taskId)
			return false;
		if (taskLocation == null) {
			if (other.taskLocation != null)
				return false;
		} else if (!taskLocation.equals(other.taskLocation))
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (taskTime == null) {
			if (other.taskTime != null)
				return false;
		} else if (!taskTime.equals(other.taskTime))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() { // prints out a nice list instead of memory location
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskDate=" + taskDate + ", taskTime=" + taskTime
				+ ", taskLocation=" + taskLocation + ", user=" + user + "]";
	}
}
