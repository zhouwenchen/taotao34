package com.taotao.manage.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manage.pojo.PicUploadResult;
import com.taotao.manage.service.PropertiesService;

/**
 * 图片上传的组件
 * @author zwc
 * @date 2017年9月18日上午10:03:27
 */
@Controller
@RequestMapping("pic")
public class PicUploadController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PicUploadController.class);
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	// 允许上传的文件格式
	private static final String[] IMAGE_TYPE= new String[]{".bmp",".jpg",".jpeg",".gif",".png"};
	
	@Autowired
	private PropertiesService propertiesService;
	
	/**
	 * 文件上传，
	 * @param uploadFile
	 * @param response
	 * @return	返回文本类型的json数据
	 * @throws Exception 
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String upload(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletResponse response) throws Exception {
		// 1.验证图片的格式是否正确
		boolean isLegal = false;
		for(String type : IMAGE_TYPE){
			if(StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)){
				isLegal = true;
				break;
			}
		}
		
		// 封装Result对象，并且将文件的byte数组放置到result对象中
		PicUploadResult fileUploadResult = new PicUploadResult();
		
		fileUploadResult.setError(isLegal ? 0 : 1);
		
		// 文件新路径
		String filePath = getFilePath(uploadFile.getOriginalFilename());
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("pic file upload .[{}] to [{}] .",uploadFile.getOriginalFilename(),filePath);
		}
		
		// 生成图片的绝对的引用地址
		String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, propertiesService.REPOSITION_PATH), "\\", "/");
		
		fileUploadResult.setUrl(propertiesService.IMAGE_BASE_URL + picUrl);
		
		File newfile = new File(filePath);
		
		// 写到磁盘
		uploadFile.transferTo(newfile);
		
		// 校验图片是否合法
		isLegal = false;
		try {
			BufferedImage image = ImageIO.read(newfile);
			if(image != null){
				fileUploadResult.setWidth(image.getWidth()+"");
				fileUploadResult.setHeight(image.getHeight()+"");
				isLegal = true;
			}
		} catch (Exception e) {
			LOGGER.error("上传的图片不合法");
		}
		
		// 状态
		fileUploadResult.setError(isLegal ? 0 : 1);
		
		// 不合法，将磁盘中的数据删除
		if(!isLegal){
			newfile.delete();
		}
		
		// 将java对象序列话为json字符串
		return MAPPER.writeValueAsString(fileUploadResult);
	}

	/**
	 * 获取图片的路径
	 * @param originalFilename
	 * @return
	 */
	private String getFilePath(String sourceFilename) {
		String baseFolder = propertiesService.REPOSITION_PATH + File.separator + "images";
		Date nowDate = new Date();

		String fileFolder = baseFolder + File.separator + new DateTime(nowDate).toString("yyyy") + File.separator
				+ new DateTime(nowDate).toString("MM") + File.separator + new DateTime(nowDate).toString("dd");
		
		// 创建文件
		File file = new File(fileFolder);
		// 如果目标文件不存在，则创建此文件目录
		if(!file.isDirectory()){
			file.mkdirs();
		}
		
		// 生成新的文件名
		String filename = new DateTime(nowDate).toString("yyyyMMddhhmmssSSS")+RandomUtils.nextInt(100, 9999)+"."+StringUtils.substringAfterLast(sourceFilename, ".");
		
		return fileFolder+File.separator+filename;
	}
}
