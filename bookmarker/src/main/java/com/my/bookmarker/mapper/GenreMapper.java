package com.my.bookmarker.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Genre;

public interface GenreMapper {
	public void insertGenre(@Param("item") Genre item);
	public List<Code> generateGenreId();
	public List<Genre> selectGenre(@Param("param") HashMap<String, Object> param);
}
