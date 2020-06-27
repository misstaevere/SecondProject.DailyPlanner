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

import com.qa.account.persistence.domain.User;
import com.qa.account.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService service;

	@Autowired // connecting the variable/dependency to the correct object(telling spring when creating the controller to inject the service into it)
	public UserController(UserService service) { // creates a new TaskController object and chucking TaskService @Bean into it
		super();
		this.service = service;
	}

	// ResponseEntity is for the HttpStatus
	@PostMapping("/create") // makes this method available at this URL, send the task (Postman) in Json format
	public ResponseEntity<User> create(@RequestBody User user) { // @RB pulls the body out and convert to task obj
		return new ResponseEntity<User>(this.service.create(user), HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<User>> read() {
		return new ResponseEntity<List<User>>(this.service.read(), HttpStatus.OK);
	}
	
	@GetMapping("/read/{userId}")
	public ResponseEntity<User> readOne(@PathVariable Long userId) {
		return ResponseEntity.ok(this.service.read(userId));
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody User user) {
		return new ResponseEntity<User>(this.service.update(user, userId), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{userId}") // boolean Httpstatus is different:
	public ResponseEntity<?> delete(@PathVariable Long userId) {
		if (this.service.delete(userId)) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
