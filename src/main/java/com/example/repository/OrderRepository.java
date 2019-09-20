package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;

@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		return order;

	};

	public Integer insert(Order order) {
		String sql = "INSERT into orders (";
		sql += " user_id";
		sql += " ,status";
		sql += " ,total_price";
		sql += " ) VALUES  (?,?,?) RETURNING id";

		return jdbcTemplate.queryForObject(sql, Integer.class, order.getUserId(), order.getStatus(),
				order.getTotalPrice());
	}

	public void update(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String Sql = "UPDATE orders SET user_id = :userId WHERE id=:id";
		template.update(Sql, param);

	}

	public Order findByUserIdStatus0(Integer userId) {
		String sql = "SELECT id,user_id,status,total_price,order_date,"
				+ "destination_name,destination_email,destination_zipcode,"
				+ "destination_address,destination_tel,delivery_time,payment_method from orders WHERE user_id=:userId AND status=0";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		Order order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
		return order;
	}

	public void deleteByAssumedId(Integer assumedId) {
		String Sql = "DELETE FROM orders WHERE user_id =:assumedId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("assumedId", assumedId);
		template.update(Sql, param);
	}
}
