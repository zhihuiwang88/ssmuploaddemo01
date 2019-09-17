package com.nengliang.web.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nengliang.web.dao.ScholasticMapper;
import com.nengliang.web.entity.Scholastic;

@Service
public class ScholasticServiceImpl implements ScholasticService {

	@Autowired
	private ScholasticMapper scholasticMapper;


	/**
	 * 插入图片
	 */
	public void insertImage(Scholastic scholastic) {
		
		scholasticMapper.insertImage(scholastic);
	}
	/**
	 * 查找图片
	 */
	public String findByName(String fileNewName) {
		String fileName = scholasticMapper.findByName(fileNewName);
		return fileName;
	}

	

	

}
