package com.qa.account.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.account.exceptions.TaskNotFoundException;
import com.qa.account.persistence.domain.User;
import com.qa.account.persistence.repo.UserRepo;

@Service
public class UserService {
	
	private UserRepo repo; // TaskRepo is data access class where all the methods go

	public UserService(UserRepo repo) {
		super();
		this.repo = repo;
	}

	// INSERT INTO task VALUES (...);
	public User create(User user) {
		return this.repo.save(user);
	}

	// SELECT * FROM task;
	public List<User> read() {
		return this.repo.findAll();
	}
	
	// SELECT * FROM task WHERE id = ;
	public User read(long userId) {
		return this.repo.findById(userId).orElseThrow(() -> new TaskNotFoundException());
	}

	// UPDATE task set name = ..;
	public User update(User user, long userId) {
		User toUpdate = this.repo.findById(userId)
				.orElseThrow(() -> new TaskNotFoundException());
		
		toUpdate.setUsername(user.getUsername());
		toUpdate.setPassword(user.getPassword());
		
		return this.repo.save(toUpdate);
		
	}

	// DELETE FROM task WHERE id = ?;
	public boolean delete(Long userId) {
		this.repo.deleteById(userId);
		return this.repo.existsById(userId);
	}

}
