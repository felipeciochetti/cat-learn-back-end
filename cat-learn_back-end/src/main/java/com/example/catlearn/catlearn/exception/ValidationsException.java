package com.example.catlearn.catlearn.exception;

public class ValidationsException  extends  RuntimeException {
	
	
	public ValidationsException(String message) {

        super(String.format(message));
    }

}
