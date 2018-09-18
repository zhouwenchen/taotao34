package com.taotao.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.EasyUIResult;
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
	
	private static Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
	
	/**
	 * 保存商品的信息
	 * @param item 商品信息
	 * @param desc 商品描述信息
	 * @param itemParams 商品的规格参数信息
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item, @RequestParam("desc") String desc,String itemParams) {
		// 1.验证参数的有效性
		if (StringUtils.isEmpty(item.getTitle())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Boolean bool = itemService.saveItem(item, desc,itemParams);
		if (bool) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 查询商品的详细列表，根据更新时间倒序排序
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<EasyUIResult> queryItemList(@RequestParam("page")Integer page,
			@RequestParam("rows")Integer rows){
		LOGGER.info("查询商品的详细列表信息 page = {},每页显示的数据条数 rows = {}",page,rows);
		try {
			PageInfo<Item> pageInfo = itemService.queryPageListByOrderBy(page, rows, Item.class, "updated desc");
			EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
			LOGGER.info("查询商品商品详情成功{}",pageInfo.getTotal());
			return ResponseEntity.ok(easyUIResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 更新商品的信息,描述，和规格参数的
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateItem(Item item,@RequestParam("desc") String desc,String itemParams){
		LOGGER.info("=======更新商品的信息开始=========item = {},描述信息 desc = {}",item, desc);
		try {
			// 1. 验证参数的有效性
			if(StringUtils.isEmpty(item.getTitle()) || StringUtils.length(item.getTitle()) > 100){
				if(LOGGER.isInfoEnabled()){
					LOGGER.info("编辑商品参数不合法,item = {},desc = {}",item,desc);
				}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			// 2. 更新商品，描述信息
			boolean b = itemService.updateItem(item,desc,itemParams);
			// 3. 响应请求信息
			if(b){
				if(LOGGER.isDebugEnabled()){
					LOGGER.info("更新商品成功，item = {},商品描述信息成功  desc = {}",item,desc);
				}
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("======更新商品的信息结束==========item = {},描述信息 desc = {}",item, desc);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
