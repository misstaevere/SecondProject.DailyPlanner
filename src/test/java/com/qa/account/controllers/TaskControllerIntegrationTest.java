package com.qa.account.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;

@RunWith(SpringRunner.class) // random port to avoid conflict issues
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // slower and launches the whole beanbag
@AutoConfigureMockMvc // sets up an object to send our fake request (what Postman does)
public class TaskControllerIntegrationTest {
	
	@Autowired // this ana will insert the object into a class
	private MockMvc mockMVC;
	
	private Task task;
	
	private Task savedTask;
	
	@Autowired
	private ObjectMapper mapper; // what we use to convert Java classes to and from JSON
	
	@Autowired
	private TaskRepo repo;
	
	@Before
	public void init() {
		this.repo.deleteAll();
		this.task = new Task("Sing", "Shower");
		this.savedTask = new Task(task.getTaskName(), task.getTaskLocation());
		this.savedTask.setTaskId(1L);
	}
	
	@Test
	public void testCreate() throws Exception {
		// REQUEST
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/task/create");
		builder.contentType(MediaType.APPLICATION_JSON); // appl. contains JSON and accepts JSON back
		builder.accept(MediaType.APPLICATION_JSON); // building our req like Postman, setting the metadata like the <head> in html
		builder.content(this.mapper.writeValueAsString(task));
		
		// WHAT WE EXPECT
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated(); // checking if status has been created
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(savedTask)); // match the content
	
		this.mockMVC.perform(builder).andExpect(matchStatus).andExpect(matchContent);
	}
	
//	@Test // same as above, just using static methods
//	public void testCreateBuilder() throws Exception {
//		this.mockMVC.perform(post("/task/create")
//		.contentType(MediaType.APPLICATION_JSON)
//		.accept(MediaType.APPLICATION_JSON)
//		.content(this.mapper.writeValueAsString(task))).andExpect(status().isCreated())
//		.andExpect(content().json(this.mapper.writeValueAsString(savedTask)));
//	}
	
	@Test
	public void testReadOneSuccess() throws JsonProcessingException, Exception {
		this.savedTask = this.repo.save(this.task); // save a task on DB, then get the ID
		
		this.mockMVC.perform(get("/task/read/" + this.savedTask.getTaskId()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content().json(this.mapper.writeValueAsString(savedTask))); // making sure it matches
	}
	
	@Test
	public void testReadOneFailure() throws Exception {
		this.mockMVC.perform(get("/task/read/999999").contentType(MediaType.APPLICATION_JSON) // tries to get flower nr 999999 -> flowerService read() throws a FlowerNotFoundEx
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound()); // Exception returns not found
	}

}
