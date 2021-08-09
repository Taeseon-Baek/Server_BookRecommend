package com.my.bookmarker.service;

import java.util.HashMap;
import java.util.List;

import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Genre;

public interface GenreService {
	public void insertGenre(Genre item);
	public List<Code> generateGenreId();
	public List<Genre> selectGenre(HashMap<String, Object> param);
}
