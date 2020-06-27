package com.qa.account.dto;

import java.util.List;

public class UserDTO {

	private long userId;
	private String username;
	private String password;
	private List<TaskDTO> task;
	
	public UserDTO() {
		super();
	}

	public UserDTO(long userId, String username, String password, List<TaskDTO> task) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.task = task;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TaskDTO> getTask() {
		return task;
	}

	public void setTask(List<TaskDTO> task) {
		this.task = task;
	}
}
