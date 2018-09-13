package com.taotao.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;

public abstract class BaseService<T extends BasePojo>{
	
//	public abstract Mapper<T> getMapper();

	@Autowired
	private Mapper<T> mapper;
	
	/**
	 * 根据id查询
	 */
	public T queryById(Long id){
		return mapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 查询所有的数据
	 * @return
	 */
	public List<T> queryAll(){
		return mapper.select(null);
	}
	
	/**
	 * 根据条件查询一条数据
	 * @param record
	 * @return
	 */
	public T queryOne(T record){
		return mapper.selectOne(record);
	}
	
	/**
	 * 根据条件查询列表
	 * @param record
	 * @return
	 */
	public List<T> queryListByWhere(T record){
		return mapper.select(record);
	}
	
	/**
	 * 根据条件分页查询数据列表
	 * @return
	 */
	public PageInfo<T> queryPageListByWhere(T record, Integer page, Integer rows) {
		// 设置分页信息
		PageHelper.offsetPage(page, rows);
		List<T> list = mapper.select(record);
		// 获取分页数据
		return new PageInfo<T>(list);
	}
	
	/**
	 * 保存数据
	 * @param record
	 * @return
	 */
	public Integer save(T record) {
		record.setCreated(new Date());
		record.setUpdated(record.getCreated());
		return mapper.insert(record);
	}
	
	/**
	 * 有选择性的保存数据（选择不为null字段保存）
	 */
	public Integer saveSelective(T record) {
		record.setCreated(new Date());
		record.setUpdated(record.getCreated());
		return mapper.insertSelective(record);
	}
	
	/**
	 * 更新数据,根据主键更新数据
	 * @param record
	 * @return
	 */
	public Integer update(T record) {
		record.setUpdated(new Date());
		return mapper.updateByPrimaryKey(record);
	}
	
	/**
	 * 有选择性的更新数据，（选择不为null的字段更新数据）
	 * @return
	 */
	public Integer updateSelective(T record){
		record.setCreated(null);
		record.setUpdated(new Date());
		return mapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 删除数据,根据id删除
	 * @param id
	 * @return
	 */
	public Integer delete(Long id){
		return mapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据ids，批量删除数据
	 * @param ids
	 * @param clazz
	 * @param property
	 * @return
	 */
	public Integer deleteByIds(List<Object> ids, Class<T> clazz, String property) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(property, ids);
		return mapper.deleteByExample(example);
	}
	
	/**
	 * 根据条件删除数据
	 * @param record
	 * @return
	 */
	public Integer deleteByWhere(T record){
		return mapper.delete(record);
	}
	
	/**
	 * 查询分页数据，并根据某个字段进行排序
	 * @param page 分页
	 * @param rows 每页显示条数
	 * @param clazz 
	 * @param orderByClause 排序字段
	 * @return
	 */
	public PageInfo<T> queryPageListByOrderBy(Integer page, Integer rows, Class<T> clazz, String orderByClause) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		Example example = new Example(clazz);
		example.setOrderByClause(orderByClause);
		List<T> list = mapper.selectByExample(example);
		return new PageInfo<T>(list);
	}
}