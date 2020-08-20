package com.epam.dsb.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class OrderItem {

	@NotNull(message = "ProductName cannot be empty")
	private String productName;
	@NotNull(message = "ProductCode cannot be empty")
	private String productCode;
	
	@NotNull(message="quantity value should be 1 or greater")	
	@Range(min = 1)
	private Integer quantity;

	public OrderItem() {

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
