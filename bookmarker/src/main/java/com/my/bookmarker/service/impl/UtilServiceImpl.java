package com.my.bookmarker.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.bookmarker.mapper.BookMapper;
import com.my.bookmarker.service.UtilService;
import com.my.bookmarker.vo.util.GenrePercent;
import com.my.bookmarker.vo.vanilla.Book;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

@Service
public class UtilServiceImpl implements UtilService {
	public static final Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
	
	@Autowired
	private BookMapper mapperBook;

	@Override
	public HashMap<String, Integer> extractNoun(String content) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		KomoranResult analyzeResultList = komoran.analyze(content);

		List<Token> tokenList = analyzeResultList.getTokenList();
		int freq = 0;
		for (Token token : tokenList) {
			if (token.getPos().contains("NN")) {
				if (result.containsKey(token.getMorph())) {
					result.put(token.getMorph(), freq + result.get(token.getMorph()));
					freq = 0;
				} else {
					result.put(token.getMorph(), 1);
				}
			}
		}

		return result;
	}

	public HashMap<String, Integer> extractNoun(Book book) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		KomoranResult analyzeResultList;

		List<String> contents = new ArrayList<String>();
		contents.add(book.getTitle());
		contents.add(book.getContent());

		for (String content : contents) {
			analyzeResultList = komoran.analyze(content);

			List<Token> tokenList = analyzeResultList.getTokenList();
			for (Token token : tokenList) {
				if (token.getPos().contains("NN")) {

					if (result.containsKey(token.getMorph())) {
						result.put(token.getMorph(), result.get(token.getMorph()) + 1);
					} else {
						result.put(token.getMorph(), 1);
					}
				}
			}
		}

		return result;
	}

	public HashMap<String, HashMap<String, Integer>> chainBook(List<Book> bookList) {
		HashMap<String, HashMap<String, Integer>> result = new HashMap<String, HashMap<String,Integer>>();
		for (Book book : bookList) {
			for (String genre : book.getGenres().split("/")) {
				extractNoun(book).forEach((key, value) -> {
					if (result.containsKey(genre)) {
						HashMap<String, Integer> temp = result.get(genre);
						if (result.get(genre).containsKey(key)) {
							temp.put(key, temp.get(key) + value);
							result.put(genre, temp);
						} else {
							temp.put(key, value);
							result.put(genre, temp);
						}
					} else {
						result.put(genre, new HashMap<String, Integer>());
					}
				});
			}
		}
		return result;
	}

	// 책 리스트 입력 -> 장르 퍼센테이지로 출력
	public List<GenrePercent> getGenrePercent(List<Book> bookList) {
		List<GenrePercent> result = new ArrayList<GenrePercent>();
		
		HashMap<String, Integer> container = new HashMap<String, Integer>();
		
		Integer cnt = 0;
		
		// 각 책 별로 반복
		for (Book book : bookList) {
			// 책 장르들 별로 반복
			for (String genre : book.getGenres().split("/")) {
				++cnt;
				container.put(genre, (container.get(genre) == null) ? 1 : container.get(genre) + 1);
			}
		}
		final Integer cntTotal = cnt;
		
		// 컨테이너 장르 별로 반복
		container.forEach((key, value) -> {
			GenrePercent newGenre = new GenrePercent();
			newGenre.setGenre(key);
			newGenre.setPercent((float) value / (float) cntTotal);
			result.add(newGenre);
		});
		
		return result;
	}
	
	// 장르 퍼센테이지 입력 -> 책 n권 출력
	
}
