package com.fjsn.demo.userapi;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjsn.demo.userapi.entity.User;
import com.fjsn.demo.userapi.util.UserStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {
	
	@Autowired
    private MockMvc mvc;
	@Autowired
	private ObjectMapper jacksonMapper;
	
	@Test
	public void getAllTest() throws Exception {	 
	    mvc.perform(get("/users/all"))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.[*].status", everyItem(equalTo(UserStatus.ACTIVE.toString()))));
	}
	
	@Test
	public void getByIdTest() throws Exception {
		final Long id = 1L;
		
		mvc.perform(get("/users/" + id))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.firstName").value(equalTo("Fulano")))
	      .andExpect(jsonPath("$.lastName").value(equalTo("Martinez")))
		  .andExpect(jsonPath("$.dateOfBirth").value(equalTo("1990-01-01")));
	}
	
	@Test
	public void updateTest() throws Exception {
		User updateUser = new User("Pedro", "D", "1990-10-11");
		
		mvc.perform(put("/users/update/3")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jacksonMapper.writeValueAsString(updateUser)))		  
	      .andExpect(status().isOk())	      
	      .andExpect(jsonPath("$.firstName").value(equalTo("Pedro")))
	      .andExpect(jsonPath("$.lastName").value(equalTo("D")))
		  .andExpect(jsonPath("$.dateOfBirth").value(equalTo("1990-10-11")));
	}
	
	@Test
	public void addTest() throws Exception {
		User addUser = new User("Pablo", "E", "1990-11-11");
		
		mvc.perform(post("/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jacksonMapper.writeValueAsString(addUser)))		  
	      .andExpect(status().isOk())	      
	      .andExpect(jsonPath("$.firstName").value(equalTo("Pablo")))
	      .andExpect(jsonPath("$.lastName").value(equalTo("E")))
		  .andExpect(jsonPath("$.dateOfBirth").value(equalTo("1990-11-11")));
	}
	
	@Test
	public void deleteTest() throws Exception {
		Long id = 3L;
		
		mvc.perform(delete("/users/delete/" + id))		  
	      .andExpect(status().isOk());
		
		mvc.perform(get("/users/" + id))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.status").value(equalTo(UserStatus.INACTIVE.toString())));
	}
}
