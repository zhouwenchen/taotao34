package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.pojo.ItemParam;
import com.taotao.manage.pojo.ItemParamItem;

/**
 * 商品类目的Service
 */
@Service
public class ItemService extends BaseService<Item>{
	
//	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescService itemDescService;
	@Autowired
	private ItemParamItemService itemParamItemService;
	/**
	 * 添加商品详情和商品描述的信息
	 * @param item  商品详情
	 * @param desc 商品描述
	 * @param itemParams 商品规格参数
	 */
	public Boolean saveItem(Item item, String desc, String itemParams) {
		
		// 1. 商品信息
		item.setId(null);// 为了防止前端篡改，故设置为null，右数据库自动生成
		item.setStatus(1);
		Integer count1 = super.save(item);
		
		// 2.添加商品的描述信息
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		Integer count2 = itemDescService.save(itemDesc);
		
		// 3.商品规格参数数据
		ItemParamItem itemParamItem = new ItemParamItem();
		itemParamItem.setId(null);
		itemParamItem.setItemId(item.getId());
		itemParamItem.setParamData(itemParams);
		Integer count3 = itemParamItemService.save(itemParamItem);
		
		return count1.intValue() == 1 && count2.intValue() == 1 && count3.intValue() == 1;
	}
	
	/**
	 * 更新商品信息
	 * @param item 商品信息
	 * @param desc 商品描述信息
	 * @param itemParams 商品的规格参数的数据
	 * @return
	 */
	public boolean updateItem(Item item, String desc,String itemParamsdata) {
		// 强制设置不能更新的字段
		item.setCreated(null);
		item.setStatus(null);
		Integer count1 = super.updateSelective(item);
		
		// 2.商品的描述
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		Integer count2 = itemDescService.updateSelective(itemDesc);
		
		// 3.商品的规格参数的数据
		Integer count3 = itemParamItemService.updateItemParamItem(item.getId(),itemParamsdata);
		
		return count1.intValue() == 1 && count2.intValue() == 1 && count3.intValue() == 1;
	}
	
}
