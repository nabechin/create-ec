package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.service.ShowItemService;

@Controller
@RequestMapping("/top")
public class showItemController {

	@Autowired
	private ShowItemService service;

	@RequestMapping("")
	public String showItemList(Model model) {
		List<Item> itemList = service.findAll();
		List<Item> itemList1 = new ArrayList<>();
		List<List<Item>> itemList2 = new ArrayList<>();
		for (int i = 1; i <= itemList.size(); i++) {
			itemList1.add(itemList.get(i - 1));
			if (i % 3 == 0) {
				itemList2.add(itemList1);
				itemList1 = new ArrayList<>();
			}
		}
		itemList2.add(itemList1);
		model.addAttribute("itemList2", itemList2);
		return "item_list";
	}

	@RequestMapping("/findByLikeName")
	public String findByLikeName(String code, Model model) {
		List<Item> itemList = service.findByLikeName(code);
		List<Item> itemList1 = new ArrayList<>();
		List<List<Item>> itemList2 = new ArrayList<>();
		for (int i = 1; i <= itemList.size(); i++) {
			itemList1.add(itemList.get(i - 1));
			if (i % 3 == 0) {
				itemList2.add(itemList1);
				itemList1 = new ArrayList<>();
			}
		}
		itemList2.add(itemList1);
		model.addAttribute("itemList2", itemList2);
		return "item_list";
	}

	@RequestMapping("/showItemDetail")
	public String showItemDetail(Model model, Integer id) {
		Item item = service.load(id);
		List<Topping> toppingList = service.findAllOfTopping();
		model.addAttribute("item", item);
		model.addAttribute("toppingList", toppingList);
		return "item_detail";

	}

}
