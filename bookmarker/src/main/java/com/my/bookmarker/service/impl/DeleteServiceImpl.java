package com.my.bookmarker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.bookmarker.mapper.DeleteMapper;
import com.my.bookmarker.service.DeleteService;
import com.my.bookmarker.vo.vanilla.Book;
import com.my.bookmarker.vo.vanilla.Genre;
import com.my.bookmarker.vo.vanilla.Writer;

@Service
public class DeleteServiceImpl implements DeleteService {
	@Autowired
	private DeleteMapper mapper;
	
	@Override
	public void deleteBook(Book item) {
		mapper.deleteBook(item);
	}
	
	@Override
	public void deleteGenre(Genre item) {
		mapper.deleteGenre(item);
	}
	
	@Override
	public void deleteWriter(Writer item) {
		mapper.deleteWriter(item);
	}
	
}
