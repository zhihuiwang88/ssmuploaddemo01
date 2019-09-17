package com.nengliang.web.dao;

import com.nengliang.web.entity.Scholastic;
import com.nengliang.web.entity.ScholasticExample;

import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface ScholasticMapper {
	


	void insertImage(Scholastic scholastic);

	String findByName(@Param("fileNewName") String fileNewName);
}