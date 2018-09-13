package com.taotao.manage.pojo;

/**
 * 文件上传的返回结果
 * @author zwc
 * @date 2017年9月18日上午10:21:28
 */
public class PicUploadResult {

	/**
	 * 错误信息
	 */
	private Integer error;
	
	/**
	 * 保存图片的地址
	 */
	private String url;
	
	/**
	 * 图片的宽
	 */
	private String width;
	
	/**
	 * 图片的高
	 */
	private String height;

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
