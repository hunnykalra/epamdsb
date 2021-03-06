package com.epam.dsb.services;

import java.util.List;

import com.epam.dsb.beans.OrderItemBean;

public interface OrderItemServices {
	
	public List<String> createOrderItem(List<OrderItemBean> orderItemBeans,String orderId);
	
	public List<OrderItemBean> reteriveOrderItemsByorderID(String orderID);
	
	public String checkOrderItem(String productName, String productCode);
	
	public void saveOrderItemMapping(List<String> orderItemIds,String orderId);
}
