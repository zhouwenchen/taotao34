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
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.EasyUIResult;
import com.taotao.manage.pojo.ItemParam;
import com.taotao.manage.pojo.ItemParamItem;
import com.taotao.manage.service.ItemParamItemService;
import com.taotao.manage.service.ItemParamService;

/**
 * 商品模版的Controller
 * 
 * @author zwc
 * @date 2018年9月17日 下午4:58:29
 */
@RequestMapping(value="/item/param")
@Controller
public class ItemParamController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ItemParamController.class);
	
	@Autowired
	private ItemParamService itemParamService;
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	/**
	 * 查询商品规格的参数列表
	 * @param page 第几页
	 * @param rows 每页显示的条数
	 * @return
	 */
	@RequestMapping(value = "list")
	public ResponseEntity<EasyUIResult> queryItemParamByList(@RequestParam(value="page",defaultValue = "1") Integer page,
			@RequestParam(value = "rows",defaultValue = "10")Integer rows){
		LOGGER.info("====查询商品规格参数列表开始=====page = {},rows = {}",page,rows);
		try {
			// 1.直接查询
			PageInfo<ItemParam> pageInfo = itemParamService.queryPageListByOrderBy(page, rows, ItemParam.class, "updated desc");
			EasyUIResult result = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
			if(pageInfo != null){
				// 2.响应客户端请求
				if(LOGGER.isDebugEnabled()){
					LOGGER.info("查询商品的模版信息成功  result = {}",result);
				}
			}
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("====查询商品规格参数列表结束=====page = {},rows = {}",page,rows);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 根据itemId，查询商品的规格参数
	 * @param itemId 商品类目的id
	 * @return 商品规格的参数
	 */
	@RequestMapping(value = "{itemId}",method = RequestMethod.GET)
	public ResponseEntity<ItemParam> queryItemParamByItemId(@PathVariable("itemId")Integer itemId){
		LOGGER.info("====查询商品规格参数开始=====itemId = {}",itemId);
		try {
			// 1.验证参数的合法性
			if(itemId == null){
				LOGGER.error("查询商品规格参数异常 itemId = {}",itemId);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			// 2.根据itemid，查询规格参数的数据
			ItemParam itemParam = itemParamService.queryById((long)itemId);
			if(itemParam == null){
				if(LOGGER.isDebugEnabled()){
					LOGGER.info("查询商品规格参数为空  itemId = {}",itemId);
				}
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(itemParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 3.响应请求
		LOGGER.info("====查询商品规格参数结束=====itemId = {}",itemId);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 添加规格参数的模版信息
	 */
	@RequestMapping(value="{itemCatId}",method=RequestMethod.POST)
	public ResponseEntity<Void> saveItemParam(@PathVariable("itemCatId")Integer itemCatId,String paramData){
		LOGGER.info("====添加规格参数的模版信息 开始=====itemId = {}",itemCatId);
		try {
			// 1.验证参数的合法性
			if(itemCatId == null || paramData == null){
				LOGGER.error("添加规格参数的模版信息  参数异常 itemId = {},paramData = {}",itemCatId, paramData);
			}
			// 2.保存规格参数的信息
			ItemParam itemParam = new ItemParam();
			itemParam.setId(null);
			itemParam.setItemCatId((long)itemCatId);;
			itemParam.setParamData(paramData);
			Integer count1 = itemParamService.save(itemParam);
			
			// 3.响应客户端
			if(count1.intValue() == 1){
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("添加规格参数的模版信息  异常 itemId = {},paramData = {}",itemCatId, paramData);
		}
		LOGGER.info("====添加规格参数的模版信息 结束=====itemId = {}",itemCatId);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
