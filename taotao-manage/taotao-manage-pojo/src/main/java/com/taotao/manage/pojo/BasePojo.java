package com.taotao.manage.pojo;

import java.util.Date;

/**
 * 抽象的basePojo类
 * 
 * @author zwc
 * @date 2017年9月16日上午9:30:47
 */
public abstract class BasePojo {

	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * 更新时间
	 */
	private Date updated;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
 }
