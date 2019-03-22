package com.fjsn.demo.userapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjsn.demo.exception.UserNotFoundException;
import com.fjsn.demo.userapi.entity.User;
import com.fjsn.demo.userapi.repo.UserRepo;
import com.fjsn.demo.userapi.util.UserStatus;

@Service
public class UserService {

	private final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepo repo;
	
	public List<User> getAll() {
		List<User> activeUsers = repo.findByStatus(UserStatus.ACTIVE.toString());
		LOG.info("Active users found = " + activeUsers.size());
		
		return activeUsers;
	}
	
	public User getById(Long id) {
		return repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	public User addUser(User user) {
		if (!isValidUser(user)) {
			throw new IllegalArgumentException("Received user is not valid: " + user);
		}
		
		return repo.save(user);
	}
	
	public User updateUser(User newUser, Long id) {
		if (!isValidUser(newUser)) {
			throw new IllegalArgumentException("Received user is not valid: " + newUser);
		}
		
		return repo.findById(id).map(user -> {
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setDateOfBirth(newUser.getDateOfBirth());
			
			return repo.save(user);
		}).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	public void deleteUser(Long id) {
		User inactiveUser = repo.findById(id).map(user -> {
			user.setStatus(UserStatus.INACTIVE.toString());
			return repo.save(user);
		}).orElseThrow(() -> new UserNotFoundException(id));
		
		LOG.info("User " + inactiveUser + " has been deactivated successfully");
	}
	
	private boolean isValidUser(User user) {
		if (user == null || user.getFirstName() == null || user.getFirstName().trim().equals("") ||
				user.getLastName() == null || user.getLastName().trim().equals("") ||
				user.getDateOfBirth() == null || user.getDateOfBirth().trim().equals("")) {
			
			return false;
		}
		
		return true;
	}
}
