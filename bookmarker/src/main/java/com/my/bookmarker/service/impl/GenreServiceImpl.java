package com.my.bookmarker.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.bookmarker.mapper.GenreMapper;
import com.my.bookmarker.service.GenreService;
import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Genre;

@Service
public class GenreServiceImpl implements GenreService{
	@Autowired
	private GenreMapper mapper;

	@Override
	public void insertGenre(Genre item) {
		mapper.insertGenre(item);
	}

	@Override
	public List<Code> generateGenreId() {
		return mapper.generateGenreId();
	}

	@Override
	public List<Genre> selectGenre(HashMap<String, Object> param) {
		return mapper.selectGenre(param);
	}
	
}
