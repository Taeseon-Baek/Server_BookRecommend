package com.my.bookmarker.service;

import java.util.HashMap;
import java.util.List;

import com.my.bookmarker.vo.util.GenrePercent;
import com.my.bookmarker.vo.vanilla.Book;

public interface UtilService {
	public HashMap<String, Integer> extractNoun(String content);
	public HashMap<String, HashMap<String, Integer>> chainBook(List<Book> bookList);
	public List<GenrePercent> getGenrePercent(List<Book> bookList);
}
