package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.pojo.ItemParam;

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
	private ItemParamService itemParamService;
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
		ItemParam itemParam = new ItemParam();
		itemParam.setId(null);
		itemParam.setItemCatId(item.getId());
		itemParam.setParamData(itemParams);
		Integer count3 = itemParamService.save(itemParam);
		
		return count1.intValue() == 1 && count2.intValue() == 1 && count3.intValue() == 1;
	}
	
	/**
	 * 更新商品信息
	 * @param item 商品信息
	 * @param desc 商品描述信息
	 * @return
	 */
	public boolean updateItem(Item item, String desc) {
		// 强制设置不能更新的字段
		item.setCreated(null);
		item.setStatus(null);
		Integer count1 = super.updateSelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		Integer count2 = itemDescService.updateSelective(itemDesc);
		
		return count1.intValue() == 1 && count2.intValue() == 1;
	}
	
}
