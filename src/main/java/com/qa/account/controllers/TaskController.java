package com.qa.account.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.account.dto.TaskDTO;
import com.qa.account.persistence.domain.Task;
import com.qa.account.services.TaskService;

@RestController // pre configures everything to Json format
@RequestMapping("/task")
public class TaskController {

	private TaskService service;

	@Autowired // connecting the variable/dependency to the correct object(telling spring when creating the controller to inject the service into it)
	public TaskController(TaskService service) { // creates a new TaskController object and chucking TaskService @Bean into it
		super();
		this.service = service;
	}

	// ResponseEntity is for the HttpStatus
	@PostMapping("/create") // makes this method available at this URL, send the task (Postman) in Json format
	public ResponseEntity<TaskDTO> create(@RequestBody Task task) { // @RB pulls the body out and convert to task obj
		return new ResponseEntity<TaskDTO>(this.service.create(task), HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<TaskDTO>> read() {
		return new ResponseEntity<List<TaskDTO>>(this.service.read(), HttpStatus.OK);
	}
	
	@GetMapping("/read/{taskId}")
	public ResponseEntity<TaskDTO> readOne(@PathVariable Long taskId) {
		return ResponseEntity.ok(this.service.read(taskId));
	}

	@PutMapping("/update/{taskId}")
	public ResponseEntity<TaskDTO> update(@PathVariable Long taskId, @RequestBody Task task) {
		return new ResponseEntity<TaskDTO>(this.service.update(task, taskId), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{taskId}") // boolean Httpstatus is different:
	public ResponseEntity<?> delete(@PathVariable Long taskId) {
		if (this.service.delete(taskId)) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
