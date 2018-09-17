package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemParamMapper;
import com.taotao.manage.pojo.ItemParam;

/**
 * 商品的模版表
 * @author zwc
 * @date 2018年9月17日 下午4:56:03
 */
@Service
public class ItemParamService extends BaseService<ItemParam>{
	
	@Autowired
	private ItemParamMapper itemParamMapper;
}
