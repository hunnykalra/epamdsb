package com.epam.dsb.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.dsb.util.StatusCodeEnum;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity handleValidationExceptions(HttpMessageNotReadableException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", ex.getLocalizedMessage());
		errors.put("status", StatusCodeEnum.UNEXPECTED_ERROR.getMessage());
		return this.buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(OrderCreationFailed.class)
	public ResponseEntity handleOrderCreationsExceptions(OrderCreationFailed ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", ex.getLocalizedMessage());
		errors.put("status", StatusCodeEnum.UNEXPECTED_ERROR.getMessage());
		return this.buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity buildResponseEntity(Map<String, String> map ,HttpStatus httpStatus) {
		return new ResponseEntity<>(map,httpStatus);
	}
	
	
}
