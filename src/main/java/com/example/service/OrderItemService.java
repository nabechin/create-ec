package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;

	@Autowired
	private OrderToppingService orderToppingService;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public Integer insert(OrderItem orderItem) {
		Integer orderItemId = repository.insert(orderItem);
		orderItem.setId(orderItemId);
		for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
			orderTopping.setOrderItemId(orderItemId);
			Integer orderToppingId = orderToppingService.insert(orderTopping);
			orderTopping.setId(orderToppingId);
		}
		return orderItemId;
	}

	public List<OrderItem> findByOrderId(Integer orderId) {
		return orderItemRepository.findByOrderId(orderId);
	}

}
