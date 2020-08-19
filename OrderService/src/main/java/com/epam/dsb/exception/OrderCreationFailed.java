package com.epam.dsb.exception;

public class OrderCreationFailed extends RuntimeException{
	
	public OrderCreationFailed(String message) {
		super(message);
	}

}
