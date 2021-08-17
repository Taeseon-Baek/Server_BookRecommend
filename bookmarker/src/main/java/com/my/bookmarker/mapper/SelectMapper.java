package com.my.bookmarker.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Book;
import com.my.bookmarker.vo.vanilla.Genre;
import com.my.bookmarker.vo.vanilla.Writer;

public interface SelectMapper {
	
	public List<Code> selectBookId();
	public List<Book> selectBook(@Param("title") String title);
	public List<Book> searchByWriter(@Param("author") String author);
	public List<Book> searchByGenre(@Param("genre") String genre);
	
	public List<Code> generateGenreId();
	public List<Genre> selectGenre(@Param("param") HashMap<String, Object> param);

	public List<Code> selectWriterId();
	public List<Writer> selectWriter(@Param("param") HashMap<String, Object> param);
}
