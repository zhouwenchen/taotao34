package com.taotao.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;

/**
 * 商品的Controller
 * 
 * @author zwc
 * @date 2018年9月13日 下午4:05:08
 */
@RequestMapping(value = "item")
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item, @RequestParam("desc") String desc) {
		// 1.验证参数的有效性
		if (StringUtils.isEmpty(item.getTitle())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Boolean bool = itemService.saveItem(item, desc);
		if (bool) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}
}
