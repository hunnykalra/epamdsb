package com.epam.dsb.bean;

import javax.validation.constraints.NotNull;

public class OrderItem {

	@NotNull(message = "ProductName cannot be empty")
	private String productName;
	@NotNull(message = "ProductCode cannot be empty")
	private String productCode;
	@NotNull(message = "Quantity cannot be empty")
	private int quantity;

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
