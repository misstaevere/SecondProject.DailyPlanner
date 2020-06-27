package com.qa.account.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.account.exceptions.TaskNotFoundException;
import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;

@Service // doen't do anything special, just for readability
public class TaskService {

	private TaskRepo repo; // TaskRepo is data access class where all the methods go

	public TaskService(TaskRepo repo) {
		super();
		this.repo = repo;
	}

	// INSERT INTO task VALUES (...);
	public Task create(Task task) {
		return this.repo.save(task);
	}

	// SELECT * FROM task;
	public List<Task> read() {
		return this.repo.findAll();
	}
	
	// SELECT * FROM task WHERE id = ;
	public Task read(long taskId) {
		return this.repo.findById(taskId).orElseThrow(() -> new TaskNotFoundException());
	}

	// UPDATE task set name = ..;
	public Task update(Task task, long taskId) {
		Task toUpdate = this.repo.findById(taskId).orElseThrow(() -> new TaskNotFoundException());
		
		toUpdate.setTaskDate(task.getTaskDate());
		toUpdate.setTaskTime(task.getTaskTime());
		toUpdate.setTaskName(task.getTaskName());
		toUpdate.setTaskLocation(task.getTaskLocation());
		
		return this.repo.save(toUpdate);
		
	}

	// DELETE FROM task WHERE id = ?;
	public boolean delete(Long taskId) {
		this.repo.deleteById(taskId);
		return this.repo.existsById(taskId);
	}
}
