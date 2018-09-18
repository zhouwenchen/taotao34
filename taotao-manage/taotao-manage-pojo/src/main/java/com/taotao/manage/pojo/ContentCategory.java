package com.taotao.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 内容分类
 * 
 * @author zwc
 * @date 2017年9月21日下午3:02:09
 */
@Table(name = "tb_content_category")
public class ContentCategory extends BasePojo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "parent_id")
	private Long parentId;
	
	private String name;
	
	private Integer status;
	
	@Column(name = "sort_order")
	private Integer sortOrder;
	
	@Column(name = "is_parent")
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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
	 * 前端EasyUI框架显示需要的字段text
	 * @return
	 */
	public String getText(){
		return this.getName();
	}
	
	/**
	 * 返回的是否是富姐点，如果是父节点的话，默认是关闭的，
	 * 如果不是子节点话，那么默认就是开启
	 * @return
	 */
	public String getState() {
		return this.getIsParent()? "closed":"open";
	}
}
