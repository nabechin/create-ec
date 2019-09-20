package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

@Service
@Transactional
public class ShowItemService {

	@Autowired
	private ItemRepository repository;

	@Autowired
	private ToppingRepository toppingRepository;

	public List<Item> findAll() {
		return repository.findAll();
	}

	public List<Item> findByLikeName(String code) {
		if (code == "") {
			return repository.findAll();
		} else {
			return repository.findByLikeName(code);
		}
	}

	public Item load(Integer id) {
		return repository.load(id);
	}

	public List<Topping> findAllOfTopping() {
		return toppingRepository.findAll();
	}
	public Topping loadTopping(Integer id) {
		return toppingRepository.load(id);
		
	}
}
