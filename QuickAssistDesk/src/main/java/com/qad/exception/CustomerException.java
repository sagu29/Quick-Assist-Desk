package com.qad.exception;
public class CustomerException extends RuntimeException{

	public CustomerException() {}
	
	public CustomerException(String message) {
		super(message);
	}
}
