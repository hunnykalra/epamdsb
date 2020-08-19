package com.epam.dsb.controller;

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
import com.epam.dsb.impl.OrderServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderServicesController {

	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@PostMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<Object> createOrder(@RequestBody Order order){		
		OrderResponse orderResponse =  orderServiceImpl.createOrder(order);
		return new ResponseEntity<Object>(orderResponse,HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") String orderId){
		Order order = orderServiceImpl.reteriveOrderById(orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
		
}
