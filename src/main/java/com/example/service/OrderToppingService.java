package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.OrderTopping;
import com.example.repository.OrderToppingRepository;

@Service
public class OrderToppingService {

	@Autowired
	private OrderToppingRepository repository;
	
	public Integer insert(OrderTopping orderTopping) {
		return repository.insert(orderTopping);
	}
	public List<OrderTopping> findByOrderItemId(int orderItemId){
		return repository.findByOrderItemId(orderItemId);
		
	}
}
