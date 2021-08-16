package com.my.bookmarker.service;

import java.util.List;

import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Book;

public interface BookService {
	
	public List<Code> selectBookId();
	public void insertBook(Book item);
	public List<Book> selectBook(String title);
	
	
	public List<Book> searchByWriter(String author);
	
	public List<Book> searchByGenre(String genre);
}
