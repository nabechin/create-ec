package com.example.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;
import com.example.domain.User;
import com.example.service.OrderItemService;
import com.example.service.OrderService;
import com.example.service.OrderToppingService;
import com.example.service.ShowItemService;
import com.example.service.UserService;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShowItemService showItemService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private OrderToppingService orderToppingService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		HttpSession session = request.getSession();

		User user = userService.findByMailAddress(request.getParameter("email"));
		// 仮useridを本idにupdate
		Order order = (Order) session.getAttribute("order");
		if (order != null && order.getUserId().equals(-1)) {

			// そのユーザの未確定のorderが残っていないか確認する
			Order orderInDb = orderService.findByUserIdStatus0(user.getId());
			if (orderInDb != null) {
				// 残っていたら、現在カートに入っているorderItemのorderIdをDBに残っているorderIdに更新する
				Integer updateCount = orderItemService.updateOrderId(order.getId(), orderInDb.getId());
			}
			order.setUserId(user.getId());

			orderService.update(order);
		}

		// 本useridの未確定のorderを取得する
		order = orderService.findByUserIdStatus0(user.getId());
		List<OrderItem> orderItemList = orderItemService.findByOrderId(order.getId());
		order.setOrderItemList(orderItemList);
		for (OrderItem orderItem : orderItemList) {

			Item item = showItemService.load(orderItem.getItemId());

			orderItem.setItem(item);

			List<OrderTopping> orderToppingList = orderToppingService.findByOrderItemId(orderItem.getId());
			orderItem.setOrderToppingList(orderToppingList);
			for (OrderTopping orderTopping : orderToppingList) {
				orderTopping.setTopping(showItemService.loadTopping(orderTopping.getToppingId()));
//				orderItem.getOrderToppingList().add(orderTopping);
			}
//			order.getOrderItemList().add(orderItem);
		}
		session.setAttribute("order", order);

		response.sendRedirect(request.getContextPath() + "/top");

	}

}
!