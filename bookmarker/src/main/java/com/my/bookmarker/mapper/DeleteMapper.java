package com.my.bookmarker.mapper;

import org.apache.ibatis.annotations.Param;

import com.my.bookmarker.vo.vanilla.Book;
import com.my.bookmarker.vo.vanilla.Genre;
import com.my.bookmarker.vo.vanilla.Writer;

public interface DeleteMapper {
	
	public void deleteBook(@Param("item") Book item);
	public void deleteGenre(@Param("item") Genre item);
	public void deleteWriter(@Param("item") Writer item);

}
