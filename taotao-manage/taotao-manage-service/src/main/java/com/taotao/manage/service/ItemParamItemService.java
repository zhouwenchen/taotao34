package com.taotao.manage.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
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
	/**
	 * 修改商品的规格参数的数据
	 * @param itemParamItem
	 * @return
	 */
	public Integer updateItemParamItem(long itemId,String itemParamsdata) {
		ItemParamItem itemParamItem = new ItemParamItem();
		itemParamItem.setParamData(itemParamsdata);
		itemParamItem.setUpdated(new Date());
		
		// 更新条件
		Example example = new Example(ItemParamItem.class);
		example.createCriteria().andEqualTo("itemId", itemId);
		
		return this.itemParamItemMapper.updateByExampleSelective(itemParamItem, example);
	}
}
