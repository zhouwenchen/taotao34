package com.taotao.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.pojo.ItemCat;

/**
 * 商品类目的Service
 */
@Service
public class ItemCatService extends BaseService<ItemCat>{
	
//	@Autowired
	private ItemCatMapper itemCatMapper;
	
	/**
	 * 根据父节点的id，查询商品类目的集合
	 * @param parentId 父节点的ID
	 * @return 指定父节点下的商品类目的集合
	 */
//	public List<ItemCat> queryItemCatByParentId(Long parentId){
//		ItemCat record = new ItemCat();
//		record.setParentId(parentId);
//		List<ItemCat> itemCats = itemCatMapper.select(record);
//		return itemCats;
//	}
	
}
