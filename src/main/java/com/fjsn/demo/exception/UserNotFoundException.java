package com.fjsn.demo.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 4561171847445790354L;

	public UserNotFoundException(Long id) {
		super("Could not find user with ID " + id);
	}
	
	public UserNotFoundException(Long id, Throwable t) {
		super("Could not find user with ID " + id, t);
	}
}
