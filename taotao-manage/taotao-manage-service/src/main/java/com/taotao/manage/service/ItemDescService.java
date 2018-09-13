package com.taotao.manage.service;

import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemDescMapper;
import com.taotao.manage.pojo.ItemDesc;

/**
 * 商品类目的Service
 */
@Service
public class ItemDescService extends BaseService<ItemDesc>{
	
//	@Autowired
	private ItemDescMapper itemDescMapper;
	
}
