package com.my.bookmarker.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.bookmarker.mapper.SelectMapper;
import com.my.bookmarker.service.SelectService;
import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Book;
import com.my.bookmarker.vo.vanilla.Genre;
import com.my.bookmarker.vo.vanilla.Writer;

@Service
public class SelectServiceImpl implements SelectService{
	
	@Autowired
	private SelectMapper mapper;
	
	@Override
	public List<Code> selectBookId() {
		// TODO Auto-generated method stub
		return mapper.selectBookId();
	}
	
	@Override
	public List<Book> selectBook(String title) {
		// TODO Auto-generated method stub
		return mapper.selectBook(title);
	}
	
	@Override
	public List<Book> searchByWriter(String author){
		
		return mapper.searchByWriter(author);	
	}
	
	@Override
	public List<Book> searchByGenre(String genre){
		
		return mapper.searchByGenre(genre);	
	}
	
	
	
	@Override
	public List<Code> generateGenreId() {
		return mapper.generateGenreId();
	}

	@Override
	public List<Genre> selectGenre(HashMap<String, Object> param) {
		return mapper.selectGenre(param);
	}
	
	
	
	@Override
	public List<Code> selectWriterId() {
		return mapper.selectWriterId();
	}
	
	@Override
	public List<Writer> selectWriter(HashMap<String, Object> param) {
		return mapper.selectWriter(param);
	}

}
