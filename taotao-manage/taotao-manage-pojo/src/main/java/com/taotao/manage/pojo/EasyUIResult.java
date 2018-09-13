package com.taotao.manage.pojo;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 封装easyUI的返回的数据
 * 
 * @author zwc
 * @date 2017年9月14日下午6:49:54
 */
public class EasyUIResult {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 总的数据量
	 */
	private long total;
	
	/**
	 * 封装的数据
	 */
	private List<?> rows;

	public EasyUIResult() {
		super();
	}

	public EasyUIResult(long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	/**
	 * Object 的集合的转化
	 */
	public static EasyUIResult formatToList(String jsonData,Class<?> clazz){
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("rows");
			List<?> list = null;
			if(data.isArray() && data.size() > 0){
				list = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return new EasyUIResult(jsonNode.get("total").intValue(),list);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return "EasyUIResult [total=" + total + ", rows=" + rows + "]";
	}
}
