package com.epam.dsb.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItemEntity {
	
	@Id	
	@Column(name="id")
	private String id;
	
	@Column(name="product_name",unique = true)	
	private String productName;
	@Column(name="product_code",unique = true)
	private String productCode;
	@Column(name="quantity")
	private Integer quantity;
	
	public OrderItemEntity() {
		id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
