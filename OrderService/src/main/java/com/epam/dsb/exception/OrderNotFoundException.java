package com.epam.dsb.exception;

public class OrderNotFoundException extends RuntimeException {
	
	public OrderNotFoundException(String message) {
		super(message);
	}
}
