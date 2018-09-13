package com.taotao.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品类目
 * @author zwc
 * @date 2017年9月16日上午9:38:31
 */
@Table(name="tb_item_cat")
public class ItemCat extends BasePojo{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 父节点id
	 */
	private Long parentId;
	
	/**
	 * 类目名称
	 */
	private String name;
	
	/**
	 * 类目的状态
	 */
	private String status;
	
	/**
	 * 排序
	 */
	private Integer sortOrder;
	
	/**
	 * 是否是父节点
	 */
	private Boolean isParent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
	/**
	 * 用于显示类目的名称
	 * easyUi的显示需要text的字段
	 */
	public String getText(){
		return this.getName();
	}
	
	/**
	 * 返回的是否是富姐点，如果是父节点的话，默认是关闭的，
	 * 如果不是子节点话，那么默认就是开启
	 */
	public String getState() {
		return this.getIsParent()? "closed":"open";
	}
}
