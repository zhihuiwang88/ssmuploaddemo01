package com.nengliang.web.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.nengliang.web.entity.Scholastic;

public interface ScholasticService {

	

	void insertImage(Scholastic scholastic);

	String findByName(String fileNewName);

}
