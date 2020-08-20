package com.epam.dsb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.dsb.entity.OrdersEntity;

@Repository
public interface OrderDao extends CrudRepository<OrdersEntity,String>{
	
	
	OrdersEntity findByOrderId(String orderId);
	
}
