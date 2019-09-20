package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;

@Repository
public class OrderToppingRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<OrderTopping> ORDERTOPPING_ROW_MAPPER = (rs, i) -> {
		OrderTopping orderTopping = new OrderTopping();
		orderTopping.setId(rs.getInt("id"));
		orderTopping.setToppingId(rs.getInt("topping_id"));
		orderTopping.setOrderItemId(rs.getInt("order_item_id"));
		return orderTopping;
	};

	public Integer insert(OrderTopping orderTopping) {
		String sql = "INSERT into order_toppings (";
		sql += " topping_id";
		sql += " ,order_item_id";
		sql += " ) VALUES (?,?) RETURNING id";

		return jdbcTemplate.queryForObject(sql, Integer.class, orderTopping.getToppingId(),
				orderTopping.getOrderItemId());
	}

	public List<OrderTopping> findByOrderItemId(Integer orderItemId) {
		String Sql = "SELECT id,topping_id,order_item_id FROM order_toppings WHERE order_item_id=:orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		List<OrderTopping> orderToppingList = template.query(Sql, param, ORDERTOPPING_ROW_MAPPER);
		return orderToppingList;

	}

}
