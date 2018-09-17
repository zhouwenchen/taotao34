package com.taotao.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品参数模版
 * @author zwc
 * @date 2017年9月18日下午4:47:17
 */
@Table(name = "tb_item_param")
public class ItemParam extends BasePojo {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 商品类目的id
	 */
	@Column(name = "item_cat_id")
	private Long itemCatId;
	
	/**
	 * 模版数据
	 */
	@Column(name = "param_data")
	private String paramData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemCatId() {
		return itemCatId;
	}

	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
}
