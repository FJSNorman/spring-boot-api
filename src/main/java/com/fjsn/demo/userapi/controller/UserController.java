package com.fjsn.demo.userapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fjsn.demo.userapi.entity.User;
import com.fjsn.demo.userapi.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/all")
	List<User> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	User getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@PostMapping("/add")
	User addUser(@RequestBody User newUser) {
		return service.addUser(newUser);
	}
	
	@PutMapping("/update/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		return service.updateUser(newUser, id);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
	}
}
