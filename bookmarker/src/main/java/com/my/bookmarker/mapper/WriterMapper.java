package com.my.bookmarker.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Writer;

public interface WriterMapper {
	public List<Code> selectWriterId();
	public void insertWriter(@Param("item") Writer item);
	public List<Writer> selectWriter(@Param("param") HashMap<String, Object> param);
	public void incFreq(@Param("id") String id);
	
}
