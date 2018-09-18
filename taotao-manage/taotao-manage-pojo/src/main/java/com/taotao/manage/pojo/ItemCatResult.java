package com.taotao.manage.pojo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商品类目回显的结果
 * 
 * @author zwc
 * @date 2017年9月20日上午11:31:03
 */
public class ItemCatResult {
	
	@JsonProperty("data")// 序列化为json时的名称
	private List<ItemCatData> itemCats = new ArrayList<>();

	public List<ItemCatData> getItemCats() {
		return itemCats;
	}

	public void setItemCats(List<ItemCatData> itemCats) {
		this.itemCats = itemCats;
	}
}
