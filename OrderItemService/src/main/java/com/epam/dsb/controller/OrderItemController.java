package com.epam.dsb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.epam.dsb.beans.OrderItemBean;
import com.epam.dsb.impl.OrderItemServiceImpl;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

	@Autowired
	OrderItemServiceImpl orderItemServiceImpl;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createOrderItem(@RequestBody List<OrderItemBean> orderItemBean,
			@RequestParam("orderId") String orderId) {
		List<String> ordeItems = orderItemServiceImpl.createOrderItem(orderItemBean, orderId);
		if (ordeItems.size() != 0)
			return new ResponseEntity<Object>(HttpStatus.OK);
		else
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value="/{orderId}")
	public ResponseEntity<Object> reteriveOrderItemByOrderId(@PathVariable("orderId") String orderID){
		List<OrderItemBean> orderItemBeans = orderItemServiceImpl.reteriveOrderItemsByorderID(orderID);
		return new ResponseEntity<Object>(orderItemBeans,HttpStatus.OK);
	}
	
}
