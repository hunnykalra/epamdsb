package com.epam.dsb.beans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class OrderItemBean {

	public OrderItemBean() {

	}

	public OrderItemBean(String productCode, String productName, Integer quantity) {
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
	}

	@NotNull(message = "please provide productcode")
	private String productCode;
	@NotNull(message = "please provide productName")
	private String productName;

	@NotNull(message = "quantity value should be 1 or greater")
	@Range(min = 1)
	private Integer quantity;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
