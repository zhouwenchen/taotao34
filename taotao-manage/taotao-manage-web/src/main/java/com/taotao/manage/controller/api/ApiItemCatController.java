package com.taotao.manage.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manage.pojo.ItemCatResult;
import com.taotao.manage.service.ItemCatService;

@RequestMapping("/api/item/cat")
@Controller
public class ApiItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ApiItemCatController.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * 对外提供接口，查询商品类目的数据
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<ItemCatResult> queryItemCat(){
		LOGGER.info("======对外提供的接口，查询商品类目的数据，开始============");
		ItemCatResult itemCatResult = null;
		try {
			itemCatResult = itemCatService.quaryAllToTree();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		LOGGER.info("======对外提供的接口，查询商品类目的数据，结束============ itemCatResult = {}",itemCatResult);
		return ResponseEntity.ok(itemCatResult);
	}
	
	/**
	 * 对外提供接口，查询商品类目的数据
	 * 定义一个回调的函数
	 */
//	@RequestMapping(method=RequestMethod.GET)
//	@ResponseBody
//	public String queryAllItemCat(@RequestParam(value = "callback",required= false)String callback){
//		LOGGER.info("======对外提供的接口，查询商品类目的数据，开始============");
//		ItemCatResult itemCatResult = null;
//		String json = "";
//		try {
//			itemCatResult = itemCatService.quaryAllToTree();
//			json = MAPPER.writeValueAsString(itemCatResult);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		LOGGER.info("======对外提供的接口，查询商品类目的数据，结束============ json = {}",json);
//		return callback +"("+ json +")";
//	}
}
