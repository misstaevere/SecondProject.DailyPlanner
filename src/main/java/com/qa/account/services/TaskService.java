package com.qa.account.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.account.dto.TaskDTO;
import com.qa.account.exceptions.TaskNotFoundException;
import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;

@Service // doen't do anything special, just for readability
public class TaskService {

	private TaskRepo repo; // TaskRepo is data access class where all the methods go
	private ModelMapper mapper; // convert between Entities and DTOs

	public TaskService(TaskRepo repo, ModelMapper mapper) { 
		super();
		this.repo = repo;
		this.mapper = mapper; // convert between Entities and DTOs
	}
	
	private TaskDTO mapToDTO(Task task) { // convert between Entities and DTOs in every single method
		return this.mapper.map(task, TaskDTO.class); // goes through all Task getters and setters
	}

	// INSERT INTO task VALUES (...);
	public TaskDTO create(Task task) {
		Task saved = this.repo.save(task);
		return this.mapToDTO(saved);
	}

	// SELECT * FROM task;
	public List<TaskDTO> read() {
		List<TaskDTO> dtos = new ArrayList<>();
		for (Task task : this.repo.findAll()) {
			dtos.add(this.mapToDTO(task));
		}
		return dtos;
// stream - like array method, converts the list to TaskDTOs rather than just tasks
//		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList()); DOES THE SAME AS ABOVE
	}
	
	// SELECT * FROM task WHERE id = ;
	public TaskDTO read(long taskId) {
		return this.mapToDTO(this.repo.findById(taskId).orElseThrow(() -> new TaskNotFoundException()));
	}

	// UPDATE task set name = ..;
	public TaskDTO update(Task task, long taskId) {
		Task toUpdate = this.repo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException());
		
		toUpdate.setTaskDate(task.getTaskDate());
		toUpdate.setTaskTime(task.getTaskTime());
		toUpdate.setTaskName(task.getTaskName());
		toUpdate.setTaskLocation(task.getTaskLocation());
		
		return this.mapToDTO(this.repo.save(toUpdate));
		
	}

	// DELETE FROM task WHERE id = ?;
	public boolean delete(Long taskId) {
		this.repo.deleteById(taskId);
		return this.repo.existsById(taskId);
	}
}
