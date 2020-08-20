package com.epam.dsb.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.dsb.bean.Order;
import com.epam.dsb.bean.OrderItem;
import com.epam.dsb.bean.OrderResponse;
import com.epam.dsb.client.OrderItemClient;
import com.epam.dsb.controller.OrderServicesController;
import com.epam.dsb.dao.OrderDao;
import com.epam.dsb.entity.OrdersEntity;
import com.epam.dsb.exception.OrderCreationFailed;
import com.epam.dsb.exception.OrderNotFoundException;
import com.epam.dsb.util.StatusCodeEnum;

@Service
public class OrderServiceImpl implements OrderService {

	private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	OrderDao orderDao;

	@Autowired
	OrderItemClient orderItemClient;

	@Override
	@Transactional
	public OrderResponse createOrder(Order order) {
		OrdersEntity orderEntity = new OrdersEntity();
		orderEntity.setCustomerName(order.getCustomerName());
		orderEntity.setOrderDate(order.getOrderDate());
		orderEntity.setShippingAddress(order.getShippingAddress());
		orderEntity.setTotal(order.getTotal());
		try {
			orderDao.save(orderEntity);
		} catch (Exception e) {
			logger.info("Order creation failed");
			logger.debug("Order creation failed due to {}", e.getLocalizedMessage());
			throw new OrderCreationFailed("Order Create Failed");
		}
		ResponseEntity<Object> responseEntity = orderItemClient.createOrderItem(orderEntity.getOrderId(),
				order.getOrderItems());
		if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			orderDao.delete(orderEntity);
			logger.info("Order creation failed");
			logger.debug("Order creation failed due to orderitem cannot created {}", String.valueOf(responseEntity.getStatusCode()));
			throw new OrderCreationFailed("Order Create Failed");
		}
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setCode(StatusCodeEnum.ORDER_CREATED.getCode());
		orderResponse.setMessage(StatusCodeEnum.ORDER_CREATED.getMessage());
		orderResponse.setOrderId(orderEntity.getOrderId());
		return orderResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public Order reteriveOrderById(String orderId) {
		OrdersEntity orderEntity = orderDao.findByOrderId(orderId);
		if (orderEntity != null) {
			Order order = new Order();
			order.setCustomerName(orderEntity.getCustomerName());
			order.setTotal(orderEntity.getTotal());
			order.setShippingAddress(orderEntity.getShippingAddress());
			order.setOrderDate(orderEntity.getOrderDate());			
			order.setOrderItems(getOrderItems(orderId));
			return order;
		} else {
			logger.info("Order is not Found with order id {} ", orderId);
			logger.debug("Order is not Found with order id {} ", orderId);
			throw new OrderNotFoundException("Order is not Found with order id " + orderId);
			}
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
