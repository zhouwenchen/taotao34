package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;

/**
 * 商品类目的Service
 */
@Service
public class ItemService extends BaseService<Item>{
	
//	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescService itemDescService;
	
	/**
	 * 添加商品详情和商品描述的信息
	 * @param item  商品详情
	 * @param desc 商品描述
	 */
	public Boolean saveItem(Item item, String desc) {
		item.setId(null);// 为了防止前端篡改，故设置为null，右数据库自动生成
		item.setStatus(1);
		Integer count1 = super.save(item);
		
		// 3.添加商品的描述信息
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		Integer count2 = itemDescService.save(itemDesc);
		return count1.intValue() == 1 && count2.intValue() == 1;
	}
	
}
