package com.my.bookmarker.service;

import java.util.HashMap;
import java.util.List;

import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Writer;

public interface WriterService {
	public List<Code> selectWriterId();
	public void insertWriter(Writer item);
	public List<Writer> selectWriter(HashMap<String, Object> param);
}
