package com.my.bookmarker.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.my.bookmarker.service.UtilService;
import com.my.bookmarker.vo.vanilla.Book;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

@Service
public class UtilServiceImpl implements UtilService {
	public static final Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

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

	@Override
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

}
