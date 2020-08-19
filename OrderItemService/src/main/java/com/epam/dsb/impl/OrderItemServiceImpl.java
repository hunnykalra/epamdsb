package com.epam.dsb.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dsb.beans.OrderItemBean;
import com.epam.dsb.dao.OrderItemDao;
import com.epam.dsb.entity.OrderItemEntity;
import com.epam.dsb.exception.OrderItemException;
import com.epam.dsb.services.OrderItemServices;

@Service
public class OrderItemServiceImpl implements OrderItemServices{

	@Autowired
	OrderItemDao orderItemDao;
	
	@Override
	public List<String> createOrderItem(List<OrderItemBean> orderItemBeans,String orderId) {
		List<String> orderItemIds = new ArrayList<>();
		for(OrderItemBean orderItemBean:orderItemBeans) {
			OrderItemEntity orderItemEntity = new OrderItemEntity();
			orderItemEntity.setOrderId(orderId);
			orderItemEntity.setProductCode(orderItemBean.getProductCode());
			orderItemEntity.setQuantity(orderItemBean.getQuantity());
			orderItemEntity.setProductName(orderItemBean.getProductName());
			orderItemIds.add(orderItemEntity.getId());
			orderItemDao.save(orderItemEntity);
		}
		return orderItemIds;
	}

	@Override
	public List<OrderItemBean> reteriveOrderItemsByorderID(String orderID) {
		List<OrderItemEntity> orderItemEntities = orderItemDao.findByOrderId(orderID);
		List<OrderItemBean> orderItemBeans = new ArrayList<>();
		if(orderItemEntities.size()!=0) {
			for(OrderItemEntity orderItemEntity : orderItemEntities) {
			OrderItemBean orderItemBean = new OrderItemBean();
			orderItemBean.setProductCode(orderItemEntity.getProductCode());
			orderItemBean.setProductName(orderItemEntity.getProductName());
			orderItemBean.setQuantity(orderItemEntity.getQuantity());
			orderItemBeans.add(orderItemBean);
			}
			return orderItemBeans;
		}else
			throw new OrderItemException("Order Item not found");
		
	}

}
