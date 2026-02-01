package com.example.spiltmate.backend.exception;


public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
