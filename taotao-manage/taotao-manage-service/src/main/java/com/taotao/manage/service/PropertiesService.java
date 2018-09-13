package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 加載路径配置文件
 * @author zwc
 * @date 2017年9月18日下午2:11:07
 */
@Service
public class PropertiesService {
	
	@Value("${REPOSITION_PATH}")
	public String REPOSITION_PATH;
	
	@Value("${IMAGE_BASE_URL}")
	public String IMAGE_BASE_URL;
}
