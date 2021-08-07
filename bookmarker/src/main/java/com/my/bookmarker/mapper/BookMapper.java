package com.my.bookmarker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.bookmarker.vo.vanilla.Book;

public interface BookMapper {
	
	public void insertBook(@Param("item") Book item);
	
	public List<Book> selectBook();
}
