package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	public Integer insert(Order order) {
		return repository.insert(order);
	}

	public void update(Order order) {
		repository.update(order);

	}

	public Order findByUserIdStatus0(Integer userId) {
		return repository.findByUserIdStatus0(userId);
	}

	public void deleteByAssumedId(Integer assumedId) {
		repository.deleteByAssumedId(assumedId);
	}

	public void deleteByNewOrderId(Integer newOrderId) {
		repository.deleteByNewOrderId(newOrderId);

	}
	public List<Order> findByUserIdStatus0ForOrderList(Integer userId){
		return repository.findByUserIdStatus0ForOrderList(userId);
		
	}

}
