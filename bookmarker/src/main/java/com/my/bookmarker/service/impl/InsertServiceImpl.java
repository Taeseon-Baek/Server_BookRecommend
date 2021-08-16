package com.my.bookmarker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.bookmarker.mapper.InsertMapper;
import com.my.bookmarker.service.InsertService;
import com.my.bookmarker.vo.vanilla.Book;
import com.my.bookmarker.vo.vanilla.Genre;
import com.my.bookmarker.vo.vanilla.Writer;

@Service
public class InsertServiceImpl implements InsertService {
	@Autowired
	private InsertMapper mapper;
	
	@Override
	public void insertBook(Book item) {
		// TODO Auto-generated method stub
		mapper.insertBook(item);
	}
	
	@Override
	public void insertGenre(Genre item) {
		mapper.insertGenre(item);
	}
	
	@Override
	public void insertWriter(Writer item) {
		mapper.insertWriter(item);
	}
	
}
