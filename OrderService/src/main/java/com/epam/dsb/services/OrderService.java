package com.epam.dsb.services;

import com.epam.dsb.bean.Order;
import com.epam.dsb.bean.OrderResponse;

public interface OrderService {

	public OrderResponse createOrder(Order order);
	
	public Order reteriveOrderById(String orderId);
}
