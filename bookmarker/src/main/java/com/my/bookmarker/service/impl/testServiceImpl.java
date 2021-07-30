package com.my.bookmarker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.bookmarker.mapper.testMapper;
import com.my.bookmarker.service.testService;
import com.my.bookmarker.vo.vanilla.Writer;

@Service
public class testServiceImpl implements testService {
	
	@Autowired
	testMapper mapper;

	@Override
	public List<Writer> selectTest01() {
		return mapper.selectTest01();
	}
	
	

}
