package com.taotao.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.manage.pojo.ItemParamItem;
import com.taotao.manage.service.ItemParamItemService;

/**
 * 商品的规格参数 
 * @author zwc
 * @date 2018年9月17日 下午4:59:06
 */
@RequestMapping(value="/item/param/item")
@Controller
public class ItemParamItemController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ItemParamItemController.class);
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	/**
	 * 根据商品的id，查询商品商品的规格参数的信息
	 * @param integer
	 * @return
	 */
	@RequestMapping(value = "{itemId}",method = RequestMethod.GET)
	public ResponseEntity<ItemParamItem> queryItemParamItem(@PathVariable("itemId") Long itemId) {
		LOGGER.info("====查询商品的规格参数的信息开始===========itemid = {}", itemId);
		try {
			// 1.验证参数的有效性
			if (itemId == null) {
				LOGGER.error("");
			}
			ItemParamItem record = new ItemParamItem();
			record.setItemId(itemId);
			ItemParamItem itemParamItem = itemParamItemService.queryOne(record);
			if (itemParamItem == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(itemParamItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("====查询商品的规格参数的信息结束===========itemid = {}", itemId);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
}
