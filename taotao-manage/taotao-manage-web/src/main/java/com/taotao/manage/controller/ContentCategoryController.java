package com.taotao.manage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.service.ContentCategoryService;

/**
 * 内容分类的Controller
 * 
 * @author zwc
 * @date 2018年9月18日 下午5:09:26
 */
@RequestMapping("content/category")
@Controller
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	private static Logger LOGGER = LoggerFactory.getLogger(ContentCategoryController.class);
	
	/**
	 * 查询内容分类的数据
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ContentCategory>> queryContentCategory(@RequestParam(value = "id",defaultValue = "0")Long parentId){
		LOGGER.info("=======查询内容分类的数据，开始=============parentId = {}",parentId);
		try {
			ContentCategory record = new ContentCategory();
			record.setParentId(parentId);
			List<ContentCategory> contentCategories = contentCategoryService.queryListByWhere(record);
			if(contentCategories == null || contentCategories.isEmpty()){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			LOGGER.info("=======查询内容分类的数据，成功结束=============parentId = {}",parentId);
			return ResponseEntity.ok(contentCategories);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("=======查询内容分类的数据，异常，结束=============parentId = {}",parentId);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 新增节点
	 * @param parentId 父节点的Id
	 * @param name 新增节点的名称
	 * @return 新增节点的信息
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ContentCategory> saveContentCategory(ContentCategory contentCategory){
		LOGGER.info("========新增内容分类节点    开始==================");
		try {
			// 1.验证参数的合法性
			if(contentCategory == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			// 2.保存内容分类节点信息
			contentCategory.setId(null);
			contentCategory.setIsParent(false);
			contentCategory.setSortOrder(1);
			contentCategory.setStatus(1);
			contentCategoryService.save(contentCategory);
			
			// 需要保证上一级为父节点
			ContentCategory parent = contentCategoryService.queryById(contentCategory.getParentId());
			if(!parent.getIsParent()){
				parent.setIsParent(true);
				this.contentCategoryService.updateSelective(parent);
			}
			
			// 3.响应请求的信息
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOGGER.info("========新增内容分类节点   结束==================");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
