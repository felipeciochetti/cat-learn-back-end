package com.example.catlearn.catlearn.exception;

public class ResourceNotFoundException  extends  RuntimeException {
	
	
	public ResourceNotFoundException(String message) {

        super(String.format(message + " not found"));
    }

}
