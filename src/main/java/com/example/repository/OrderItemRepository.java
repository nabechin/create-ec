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

@Repository
public class OrderItemRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<OrderItem> ORDERITEM_ROQ_MAPPER = (rs, i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setSize(rs.getString("size").charAt(0));
		return orderItem;
	};

	public Integer insert(OrderItem orderItem) {
		String sql = "INSERT INTO order_items (";
		sql += " item_id";
		sql += " ,order_id";
		sql += " ,quantity";
		sql += " ,size";
		sql += " ) VALUES (?,?,?,?) RETURNING id";

		return jdbcTemplate.queryForObject(sql, Integer.class, orderItem.getItemId(), orderItem.getOrderId(),
				orderItem.getQuantity(), orderItem.getSize());
	}

	public List<OrderItem> findByOrderId(Integer orderId) {
		String Sql = "SELECT id,item_id,order_id,quantity,size FROM order_items WHERE order_id=:orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
		List<OrderItem> orderItemList = template.query(Sql, param, ORDERITEM_ROQ_MAPPER);
		return orderItemList;

	}

	public Integer updateOrderId(Integer oldOrderId, Integer newOrderId) {
		return null;
	}
}
