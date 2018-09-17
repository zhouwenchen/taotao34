package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemParamItemMapper;
import com.taotao.manage.pojo.ItemParamItem;

/**
 * 商品数据规格的service
 * @author zwc
 * @date 2018年9月17日 下午4:55:38
 */
@Service
public class ItemParamItemService extends BaseService<ItemParamItem>{

	@Autowired
	private ItemParamItemMapper itemParamItemMapper;
}
