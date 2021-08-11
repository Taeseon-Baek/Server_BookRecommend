package com.my.bookmarker.service;

import java.util.HashMap;

import com.my.bookmarker.vo.vanilla.Book;

public interface UtilService {
	public HashMap<String, Integer> extractNoun(String content);
	public HashMap<String, Integer> extractNoun(Book book);
}
