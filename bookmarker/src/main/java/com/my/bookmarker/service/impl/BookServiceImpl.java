package com.my.bookmarker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.bookmarker.mapper.BookMapper;
import com.my.bookmarker.service.BookService;
import com.my.bookmarker.vo.vanilla.Book;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper mapper;

	@Override
	public void insertBook(Book item) {
		// TODO Auto-generated method stub
		mapper.insertBook(item);
	}

	@Override
	public List<Book> selectBook() {
		// TODO Auto-generated method stub
		return mapper.selectBook();
	}

}
