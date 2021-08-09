package com.my.bookmarker.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.bookmarker.mapper.WriterMapper;
import com.my.bookmarker.service.WriterService;
import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Writer;

@Service
public class WriterServiceImpl implements WriterService {
	@Autowired
	WriterMapper mapper;

	@Override
	public List<Code> selectWriterId() {
		return mapper.selectWriterId();
	}

	@Override
	public void insertWriter(Writer item) {
		mapper.insertWriter(item);
	}

	@Override
	public List<Writer> selectWriter(HashMap<String, Object> param) {
		return mapper.selectWriter(param);
	}
}
