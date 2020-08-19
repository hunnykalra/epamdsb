package com.epam.dsb.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.dsb.bean.Order;
import com.epam.dsb.bean.OrderItem;
import com.epam.dsb.bean.OrderResponse;
import com.epam.dsb.entity.OrderEntity;
import com.epam.dsb.exception.OrderCreationFailed;
import com.epam.dsb.exception.OrderNotFoundException;
import com.epam.dsb.services.OrderItemClient;
import com.epam.dsb.services.OrderService;
import com.epam.dsb.util.StatusCodeEnum;
import com.epam.dsm.dao.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	OrderItemClient orderItemClient;

	@Override
	public OrderResponse createOrder(Order order) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setCustomerName(order.getCustomerName());
		orderEntity.setOrderDate(order.getOrderDate());
		orderEntity.setShippingAddress(order.getShippingAddress());
		orderEntity.setTotal(order.getTotal());
		try {
			orderDao.save(orderEntity);
		} catch (Exception e) {
			throw new OrderCreationFailed("Order Create Failed");
		}
		ResponseEntity<Object> responseEntity = orderItemClient.createOrderItem(orderEntity.getOrderId(),
				order.getOrderItems());
		if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			orderDao.delete(orderEntity);
			throw new OrderCreationFailed("Order Create Failed");
		}
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setCode(StatusCodeEnum.ORDER_CREATED.getCode());
		orderResponse.setMessage(StatusCodeEnum.ORDER_CREATED.getMessage());
		orderResponse.setOrderId(orderEntity.getOrderId());
		return orderResponse;
	}

	@Override
	public Order reteriveOrderById(String orderId) {
		OrderEntity orderEntity = orderDao.findByOrderId(orderId);
		if (orderEntity != null) {
			Order order = new Order();
			order.setCustomerName(orderEntity.getCustomerName());
			order.setTotal(orderEntity.getTotal());
			order.setShippingAddress(orderEntity.getShippingAddress());
			order.setOrderDate(orderEntity.getOrderDate());			
			order.setOrderItems(getOrderItems(orderId));
			return order;
		} else
			throw new OrderNotFoundException("Order is not Found with order id " + orderId);
	}
	
	private List<OrderItem> getOrderItems(String orderId){
		ResponseEntity<Object> responseEntity = orderItemClient.reteriveByOrderId(orderId);
		if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {			
			throw new OrderNotFoundException("Order is not Found with order id " + orderId);
		}
		List<OrderItem> orderItems = (List<OrderItem>) responseEntity.getBody();
		return orderItems;
		
	}

}
