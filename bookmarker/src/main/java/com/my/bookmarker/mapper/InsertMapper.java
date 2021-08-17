package com.my.bookmarker.mapper;

import org.apache.ibatis.annotations.Param;

import com.my.bookmarker.vo.vanilla.Book;
import com.my.bookmarker.vo.vanilla.Genre;
import com.my.bookmarker.vo.vanilla.Writer;

public interface InsertMapper {
	
	public void insertBook(@Param("item") Book item);
	public void insertGenre(@Param("item") Genre item);
	public void insertWriter(@Param("item") Writer item);

}
