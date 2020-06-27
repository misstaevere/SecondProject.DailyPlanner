package com.qa.account.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.account.persistence.domain.Task;
import com.qa.account.services.TaskService;

@RestController // pre configures everything to Json format
public class TaskController {

	private TaskService service;

	@Autowired // connecting the variable/dependency to the correct object(telling spring when
				// creating the controller to inject the service into it)
	public TaskController(TaskService service) { // creates a new TaskController object and chucking TaskService @Bean
													// into it
		super();
		this.service = service;
	}

	@PostMapping("/create") // makes this method available at this URL, send the task (Postman) in Json
							// format
	public Task create(@RequestBody Task task) { // @RB pulls the body out and convert to task obj
		return this.service.create(task);
	}

	@GetMapping("/read")
	public List<Task> read() {
		return this.service.read();
	}

	@PutMapping("/update/{taskId}")
	public Task update(@PathVariable Long taskId, @RequestBody Task task) {
		return this.service.update(task, taskId);
	}

	@DeleteMapping("/delete/{taskId}")
	public boolean delete(@PathVariable Long taskId) {
		return this.service.delete(taskId);
	}

}
