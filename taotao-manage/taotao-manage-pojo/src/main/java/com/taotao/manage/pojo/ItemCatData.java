package com.taotao.manage.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 前台展示的商品类目的数据
 * 
 * @author zwc
 * @date 2017年9月20日上午11:31:46
 */
public class ItemCatData {

	@JsonProperty("u")
	// 序列化为json的时候为u
	private String url;

	@JsonProperty("n")
	private String name;

	@JsonProperty("i")
	private List<?> items;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}
}
