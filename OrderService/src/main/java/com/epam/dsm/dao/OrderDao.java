package com.epam.dsm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.dsb.entity.OrderEntity;

@Repository
public interface OrderDao extends CrudRepository<OrderEntity,String>{
	
	OrderEntity findByOrderId(String orderId);
	
}
