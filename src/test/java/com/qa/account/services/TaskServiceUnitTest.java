package com.qa.account.services;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;

// UNIT TEST - don't need all Spring framework to run, it only uses Junit & Mockito library, none of our Beans get loaded
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceUnitTest {

	private final Task TASK = new Task("Eat cake", "Kitchen"); // Setting up the initial variable

	private Task savedTask;

	@Mock // Mock everything that's in the constructor ()
	private TaskRepo repo;

	@InjectMocks // creating a fake TaskRepo
	private TaskService service;

	@Before
	public void init() {
		this.savedTask = new Task(TASK.getTaskName(), TASK.getTaskLocation());
		this.savedTask.setTaskId(1L); // setting up what the variable should look like after it's been saved
	}

	@Test // TASKSERVICE TEST
	public void testCreate() {

		Mockito.when(this.repo.save(TASK)) // use fake TaskRepo and send back savedTask
				.thenReturn(savedTask);

		assertEquals(savedTask, service.create(TASK));

		// test that checks the structure of the method you are testing, makins sure it
		// runs exactly once
		Mockito.verify(this.repo, Mockito.times(1)).save(TASK);
	}

	@Test // only mock one method call at a time
	public void testUpdate() { // convert into optional, because return type of findById in taskservice is
								// optional
		Mockito.when(this.repo.findById(savedTask.getTaskId()))
			.thenReturn(Optional.of(savedTask)); // find the existing savedTask that I created, toUpdate = savedTask
		
		Task newTask = new Task("Hop", "Garden"); // no id
		Task newTaskWithId = new Task("Hop", "Garden"); // update() has 2 parameters task and id
		newTaskWithId.setTaskId(savedTask.getTaskId());
		
		Mockito.when(this.repo.save(newTaskWithId)).thenReturn(newTaskWithId);

		assertEquals(newTaskWithId, this.service.update(newTask, savedTask.getTaskId()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(savedTask.getTaskId());
		Mockito.verify(this.repo, Mockito.times(1)).save(newTaskWithId);
	}
	
	@Test
	public void testDelete() { // don't need to mock deleteById because it's a void method
		final long taskId = 1L;
		final boolean RESULT = false;
		Mockito.when(this.repo.existsById(taskId)).thenReturn(RESULT);
		assertEquals(RESULT, this.service.delete(taskId));
		
		Mockito.verify(this.repo, Mockito.times(1)).existsById(taskId);
	}
}
