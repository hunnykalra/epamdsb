package com.epam.dsb.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dsb.bean.Order;
import com.epam.dsb.bean.OrderResponse;
import com.epam.dsb.services.OrderService;



@RestController
@RequestMapping("/order")
public class OrderServicesController {
	
	private Logger logger = LoggerFactory.getLogger(OrderServicesController.class);

	@Autowired
	OrderService orderServiceImpl;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createOrder(@Valid @RequestBody Order order){		
		OrderResponse orderResponse =  orderServiceImpl.createOrder(order);
		logger.info("Order created Successfully with orderId {} ",orderResponse.getOrderId());
		return new ResponseEntity<Object>(orderResponse,HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable(value = "id", required = true) String orderId){
		Order order = orderServiceImpl.reteriveOrderById(orderId);
		logger.info("Order retreive Successfully with orderId {} ",orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
		
}
