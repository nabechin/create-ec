package com.example.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.User;
import com.example.form.ShoppingCartForm;
import com.example.service.OrderItemService;
import com.example.service.OrderService;
import com.example.service.ShowItemService;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

	@Autowired
	private ShowItemService service;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public ShoppingCartForm setUpForm() {
		return new ShoppingCartForm();
	}

	@RequestMapping("/add")
	public String add(ShoppingCartForm form, Model model) {

		Order order = (Order) session.getAttribute("order");
		if (order == null) {
			order = new Order();
			order.setOrderItemList(new ArrayList<>());
			// 作成したorderをDBに書き込む
			User user = (User)session.getAttribute("user");
			order.setUserId(-1);
			if(user!=null){
				order.setUserId(user.getId());
			}
			order.setStatus(0);
			order.setTotalPrice(0);
			Integer orderId = orderService.insert(order);
			order.setId(orderId);
			session.setAttribute("order", order);
		}

		OrderItem orderItem = new OrderItem();
		orderItem.setOrderToppingList(new ArrayList<>());

		orderItem.setItemId(form.getItemId());
		orderItem.setOrderId(order.getId());
		orderItem.setQuantity(form.getQuantity());
		orderItem.setSize(form.getSize());
		orderItem.setItem(service.load(form.getItemId()));

		if (form.getToppingIds() != null) {
			for (Integer toppingId : form.getToppingIds()) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setTopping(service.loadTopping(toppingId));
				orderTopping.setToppingId(toppingId);
				orderItem.getOrderToppingList().add(orderTopping);
			}
		}
		Integer orderItemId = orderItemService.insert(orderItem);

		order.getOrderItemList().add(orderItem);

		return "redirect:/cart/show";
	}

	@RequestMapping("/show")
	public String show(ShoppingCartForm form, Model model) {

//		List<OrderItem> orderItemList = new ArrayList<>();
		Order order = (Order) session.getAttribute("order");
//		System.out.println(order.toString());
//		if (order==null) {
//			order = new Order();
//			order.setOrderItemList(new ArrayList());
//			
//		}
//		session.setAttribute("order", order);
		return "cart_list";

	}

}
