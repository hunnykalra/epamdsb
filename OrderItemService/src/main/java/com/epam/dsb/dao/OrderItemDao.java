package com.epam.dsb.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.dsb.entity.OrderItemEntity;

@Repository
public interface OrderItemDao extends CrudRepository<OrderItemEntity, String> {

	List<OrderItemEntity> findByOrderId(String orderId);
	
}
