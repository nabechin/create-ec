package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.User;

/**
 * ユーザテーブルを操作するrepositoryクラス.
 * 
 * @author yuma.watanabe
 *
 */
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setName(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getInt("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getInt("telephone"));
		return user;
	};

	/**
	 * ユーザ情報の追加
	 * 
	 * @param user ユーザ情報
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String Sql = "INSERT INTO users(name,email,password,zipcode,address,telephone) "
				+ "VALUES(:name,:email,:password,:zipcode,:address,:telephone)";
		template.update(Sql, param);
	}

	/**
	 *　ユーザ情報の検索.
	 * @param email　ユーザのメールアドレス
	 * @return　ユーザ情報
	 */
	public User findByMailAddress(String email) {
		String sql = "SELECT id,name,email,password,zipcode,address,telephone FROM users WHERE email=:email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}
}
