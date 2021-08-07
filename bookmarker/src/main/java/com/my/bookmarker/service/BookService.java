package com.my.bookmarker.service;

import java.util.List;

import com.my.bookmarker.vo.vanilla.Book;

public interface BookService {
	public void insertBook(Book item);
	public List<Book> selectBook();
}
