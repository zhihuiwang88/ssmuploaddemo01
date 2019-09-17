package com.nengliang.web.controller;

import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.fabric.xmlrpc.base.Array;
import com.nengliang.web.entity.Scholastic;
import com.nengliang.web.service.impl.ScholasticService;
import com.nengliang.web.util.JsonResult;
import com.sun.org.apache.bcel.internal.generic.NEW;


/**
 * WeChat: nhnmamtf
 * spring,springmvc,mybatis,layui
 * @author Dell
 *@Data 20190812
 *@Url : http://localhost:8081/ssmupload/
 */
@Controller
@RequestMapping("/scholastic")
public class ScholasticController {
    
	//日志打印
	private  static final Logger logger = LoggerFactory.getLogger(ScholasticController.class);
	
	@Autowired
	private ScholasticService scholasticService;
	

	//  http://localhost:8081/ssmupload/scholastic/uploadImage
	
	@RequestMapping(value="/uploadImage",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult uploadImage(MultipartFile uploadfile) throws Exception {
		JsonResult jsonResult = new JsonResult();
	/*
	 * 判断图片不能为空
	 * 判断图片类型是否正确
	 * 图片重命名
	 * 图片存入数据库
	 * 图片写到服务器
	 */
		String  fileName = uploadfile.getOriginalFilename();
		if(StringUtils.isEmpty(fileName)) {
			jsonResult.setMsg("图片为空,请选中图片上传");
			return jsonResult;
		}
		
		String fileType = fileName.substring(fileName.indexOf("."));
		if(!fileType.matches("^.*(png|jpeg|jpg)$")) {
			jsonResult.setMsg("图片格式不正确");
			return jsonResult;
		}
		
		String millis = System.currentTimeMillis() + "";
		String fileNewName = millis + fileType;
		String imgPath = "E:\\images\\";
		String filePath = imgPath + fileNewName;
		// 图片宽高不能为零
		BufferedImage  bufferedImage = ImageIO.read(uploadfile.getInputStream());
		int  height = bufferedImage.getHeight();
		int  width = bufferedImage.getWidth();
		if(height==0 || width==0) {
			jsonResult.setMsg("图片宽高为零");
			return jsonResult;
		}
		
		// 把图片存入数据库
		Scholastic  scholastic = new Scholastic();
		scholastic.setHeadportrait(filePath);
		scholasticService.insertImage(scholastic);
		
		// 把图片写到服务器
		File path = new  File(filePath);
		if(!path.exists()) {
			path.mkdirs();
		}
		uploadfile.transferTo(path);
		
		jsonResult.setUrl(filePath);
		jsonResult.setWidth(width + "");
		jsonResult.setHeight(height + "");
	    return jsonResult;
	}
	
	/**
	 * 图片下载
	 * 图片要存在
	 * 判断图片不能为空
	 * 数据库查找图片是否存在
	 * 图片重命名  E:\images\2020101519\673432.jpg
	 * 下载图片
	 * 
	 */
	@RequestMapping(value="/downImage",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult downImage(MultipartFile downfile, HttpServletResponse response) throws Exception {
		JsonResult jsonResult = new JsonResult();
	     
		String fileName= downfile.getOriginalFilename();
		if(StringUtils.isEmpty(fileName)) {
			jsonResult.setMsg("图片为空,请选中下载的图片");
			return jsonResult;
		}
		// 数据库查询图片存在不
		String filePath = "E:\\images\\";
		String fileOldName = filePath + fileName;
		String findByImage = scholasticService.findByName(fileOldName);
		if(StringUtils.isEmpty(findByImage)) {
			jsonResult.setMsg("图片不存在：" + fileOldName);
			return jsonResult;
		}
		// 图片存放新路径;
		String path = "E:\\images\\";
		String dataFilePath = new SimpleDateFormat("YYYYMMddHH").format(new Date()); 
		String fileNewPath = path + dataFilePath + "\\" +fileName;
		// 图片写到服务器
		File fileRead = new File(fileNewPath);
		if(!fileRead.exists()) {
			fileRead.mkdirs();
		}
		downfile.transferTo(fileRead);
		
		// 下载图片，设置响应头
		
		response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(fileNewPath, "UTF-8"));
		FileInputStream in =new FileInputStream(fileOldName); 
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) > 0) { 
	        // 输出缓冲区的内容到浏览器，实现文件下载 
	        out.write(buffer, 0, len); 
	      } 
		in.close();
		out.close();
		
		jsonResult.setUrl(fileNewPath);
		 return jsonResult;
	}
	
	
	// 最后一个
}
