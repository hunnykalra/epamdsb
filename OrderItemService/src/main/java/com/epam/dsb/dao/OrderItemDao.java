package com.epam.dsb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.dsb.entity.OrderItemEntity;

@Repository
public interface OrderItemDao extends CrudRepository<OrderItemEntity, String> {
	
	@Query("select orderItemEntity from OrderItemEntity orderItemEntity where orderItemEntity.id in ?1  ")
	List<OrderItemEntity> findByIds(List<String> ids);
	
	@Query("select orderItemEntity from OrderItemEntity orderItemEntity where orderItemEntity.productName = ?1 and orderItemEntity.productCode= ?2")
	OrderItemEntity  findByproductNameandproductCode(String productName,String productCode);
	
	
}
