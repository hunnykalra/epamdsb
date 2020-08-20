package com.epam.dsb.bean;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Order {

	public Order() {

	}

	@NotBlank(message = "Customer Name should be blank")
	private String customerName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Please provide date")
	private Date orderDate;

	@NotBlank(message = "Shipping Address should be blank")
	private String shippingAddress;
	
	@NotNull(message = "order items should be blank")
	@Size(min = 1)
	private List<OrderItem> orderItems;

	@Min(value = 1)
	private Integer total;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Valid
	@Size(max = 1)
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotal() {
		return total;
	}

}
