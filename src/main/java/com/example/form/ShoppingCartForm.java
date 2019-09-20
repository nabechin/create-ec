package com.example.form;

import java.util.List;

public class ShoppingCartForm {

	private Integer itemId;
	private Character size;
	private List<Integer> toppingIds;
	private Integer quantity;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public List<Integer> getToppingIds() {
		return toppingIds;
	}

	public void setToppingIds(List<Integer> toppingIds) {
		this.toppingIds = toppingIds;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
