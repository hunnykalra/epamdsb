package com.epam.dsb.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.dsb.bean.OrderItem;

@FeignClient(name = "order-item", url = "http://localhost:9009")
public interface OrderItemClient {

	@PostMapping("/orderItem")
	ResponseEntity<Object> createOrderItem(@RequestParam("orderId") String orderId,@RequestBody List<OrderItem> orderItem);
	
	@GetMapping("/orderItem/{orderId}")
	ResponseEntity<Object> reteriveByOrderId(@PathVariable("orderId")String orderId);
}
