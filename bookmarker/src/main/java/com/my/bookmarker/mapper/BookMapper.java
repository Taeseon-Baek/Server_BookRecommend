package com.my.bookmarker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Book;

public interface BookMapper {

	public List<Code> selectBookId();
	public void insertBook(@Param("item") Book item);
	public List<Book> selectBook(@Param("title") String title);
	
	
	public List<Book> findByWriter(@Param("author") String author);
	
	public List<Book> findByGenre(@Param("genre") String genre);
}
