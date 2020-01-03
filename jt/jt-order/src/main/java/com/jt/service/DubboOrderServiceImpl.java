package com.jt.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;


@Service
public class DubboOrderServiceImpl implements DubboOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Override
	@Transactional
	public String saveOrder(Order order) {
		Date date=new Date();
		
		String orderId=""+order.getUserId()+System.currentTimeMillis();
		order.setOrderId(orderId);
		order.setStatus(1);
		order.setCreated(date);
		order.setUpdated(date);
		
		OrderShipping orderShipping = order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setItemId(orderId);
			orderItem.setCreated(date);
			orderItem.setUpdated(date);
			orderItemMapper.insert(orderItem);
		}
		orderMapper.insert(order);
		orderShippingMapper.insert(orderShipping);	
		return orderId;
	}
	@Override
	public Order findById(String id) {
		Order order=orderMapper.selectById(id);
		order.setOrderShipping(orderShippingMapper.selectById(id));		 
		order.setOrderItems(orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id", id)));		
		return order;
	}
	
	
}
