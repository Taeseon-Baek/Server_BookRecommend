package com.my.bookmarker.service;

import java.util.HashMap;
import java.util.List;

import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Book;
import com.my.bookmarker.vo.vanilla.Genre;
import com.my.bookmarker.vo.vanilla.Writer;

public interface SelectService {
	
	public List<Book> selectBook(String title);
	public List<Code> selectBookId();
	public List<Book> searchByWriter(String author);
	public List<Book> searchByGenre(String genre);
	
	public List<Code> generateGenreId();
	public List<Genre> selectGenre(HashMap<String, Object> param);

	public List<Code> selectWriterId();
	public List<Writer> selectWriter(HashMap<String, Object> param);
}
