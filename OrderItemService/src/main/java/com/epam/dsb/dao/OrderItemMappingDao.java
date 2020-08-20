package com.epam.dsb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.dsb.entity.OrderItemMapping;

@Repository
public interface OrderItemMappingDao extends CrudRepository<OrderItemMapping, String>{
	
	@Query("select orderItemId from OrderItemMapping where orderId = ?1")
	List<String> findByOrderId(String orderId);
	
	
}
