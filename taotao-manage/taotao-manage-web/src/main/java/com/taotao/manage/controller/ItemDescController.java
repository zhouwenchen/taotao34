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

import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;

/**
 * ItemDesc的  controller
 * @author zwc
 * @date 2018年9月14日 下午7:20:43
 */
@RequestMapping("/item/desc")
@Controller
public class ItemDescController {
	
	@Autowired
	private ItemDescService itemDescService;
	private static Logger LOGGER = LoggerFactory.getLogger(ItemDescController.class);
	
	/**
	 * 根据 itemId 查询商品的描述信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<ItemDesc> queryItemDescByItemId(@PathVariable("id") Long itemId){
		LOGGER.info("开始查询商品的描述信息itemId = {}", itemId);
		ItemDesc itemDesc = itemDescService.queryById(itemId);
		if(itemDesc == null){
			LOGGER.info("查询商品的描述信息失败itemId = {}", itemId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		LOGGER.info("结束查询商品的描述信息itemId = {}", itemId);
		return ResponseEntity.ok(itemDesc);
	}
}
