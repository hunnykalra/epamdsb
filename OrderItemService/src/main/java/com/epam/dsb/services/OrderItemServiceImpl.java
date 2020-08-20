package com.epam.dsb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.epam.dsb.beans.OrderItemBean;
import com.epam.dsb.dao.OrderItemDao;
import com.epam.dsb.dao.OrderItemMappingDao;
import com.epam.dsb.entity.OrderItemEntity;
import com.epam.dsb.entity.OrderItemMapping;
import com.epam.dsb.exception.OrderItemException;

@Service
public class OrderItemServiceImpl implements OrderItemServices {

	private Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);

	@Autowired
	OrderItemDao orderItemDao;
	
	@Autowired
	OrderItemMappingDao orderItemMappingDao;

	@Override
	@Transactional
	public List<String> createOrderItem(List<OrderItemBean> orderItemBeans, String orderId) {
		List<String> orderItemIds = new ArrayList<>();
		for (OrderItemBean orderItemBean : orderItemBeans) {
			String orderItemId = checkOrderItem(orderItemBean.getProductName(), orderItemBean.getProductCode());
			if (StringUtils.isEmpty(orderItemId)) {
				OrderItemEntity orderItemEntity = new OrderItemEntity();				
				orderItemEntity.setProductCode(orderItemBean.getProductCode());
				orderItemEntity.setQuantity(orderItemBean.getQuantity());
				orderItemEntity.setProductName(orderItemBean.getProductName());
				orderItemDao.save(orderItemEntity);
				orderItemIds.add(orderItemEntity.getId());
			} else {
				orderItemIds.add(orderItemId);
			}

		}
		saveOrderItemMapping(orderItemIds, orderId);
		logger.info("OrderItems created successfully");
		logger.debug("OrderItems created successfully for order Id {}", orderId);
		return orderItemIds;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrderItemBean> reteriveOrderItemsByorderID(String orderID) {
		List<String> orderItemIds =orderItemMappingDao.findByOrderId(orderID); 
		List<OrderItemEntity> orderItemEntities = orderItemDao.findByIds(orderItemIds);
		List<OrderItemBean> orderItemBeans = new ArrayList<>();
		if (orderItemEntities.size() != 0) {
			orderItemBeans = orderItemEntities.stream()
					.map(ent -> new OrderItemBean(ent.getProductCode(), ent.getProductName(), ent.getQuantity()))
					.collect(Collectors.toList());
			return orderItemBeans;
		} else {
			logger.info("OrderItems not found");
			logger.debug("OrderItems not  found for order Id {}", orderID);
			throw new OrderItemException("Order Item not found");
		}

	}

	@Override
	@Transactional(propagation = Propagation.NESTED, readOnly = true)
	public String checkOrderItem(String productName, String productCode) {
		OrderItemEntity orderItemEntity = orderItemDao.findByproductNameandproductCode(productName, productCode);
		if (orderItemEntity != null)
			return orderItemEntity.getId();
		else
			return "";
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void saveOrderItemMapping(List<String> orderItemIds, String orderId) {
		for(String orderItemId:orderItemIds) {
		OrderItemMapping orderItemMapping = new OrderItemMapping();
		orderItemMapping.setOrderId(orderId);
		orderItemMapping.setOrderItemId(orderItemId);
		orderItemMappingDao.save(orderItemMapping);
		}
	}

}
