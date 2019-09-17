package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * Itemテーブルを操作するrepositoryクラス
 * 
 * @author yuma.watanabe
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {

		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};

	public List<Item> findAll() {
		String Sql = "SELECT id,name,description,price_m,price_l,image_path,deleted from items";
		List<Item> itemList = template.query(Sql, ITEM_ROW_MAPPER);
		return itemList;
	}

	public List<Item> findByLikeName(String code) {
		String Sql = "SELECT id,name,description,price_m,price_l,image_path,deleted from items WHERE name like :name";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + code + "%");
		List<Item> itemList = template.query(Sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}

	public Item load(Integer id) {
		String Sql = "SELECT id,name,description,price_m,price_l,image_path,deleted from items where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(Sql, param, ITEM_ROW_MAPPER);
		return item;
	}

}
