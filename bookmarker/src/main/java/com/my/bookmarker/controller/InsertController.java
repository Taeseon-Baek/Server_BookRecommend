package com.my.bookmarker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.bookmarker.service.BookService;
import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Book;

@RestController
@RequestMapping(value = "/insert")
public class InsertController {
	
	@Autowired
	private BookService serviceBook;
	
	private ArrayList<String> alphabet = 
			new ArrayList<String>(Arrays.asList(
					"a", "b", "c", "d", "e", "f", 
					"g", "h", "i", "j", "k", "l", 
					"m", "n", "o", "p", "q", "r", 
					"s", "t", "u", "v", "w", "x", 
					"y", "z" 
					)); 
	
	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public String test() {
		Code codeForm = serviceBook.selectBookId().get(0);
		String code = codeForm.getCodeDate();
		int cnt = codeForm.getCnt();
		int size = alphabet.size();
		while (cnt >= 0) {
			code += alphabet.get(cnt % alphabet.size());
			if (cnt == 0) {
				break;
			}
			cnt /= size;
		}
		return code;
	}
	
	@RequestMapping(value = "/book", method=RequestMethod.GET)
	public void insertBookTest() {
		Book newBook = new Book();
		
		// 코드 생성 로직: 날짜 + 26진법 코드 부착
		Code codeForm = serviceBook.selectBookId().get(0);
		String code = codeForm.getCodeDate();
		int cnt = codeForm.getCnt();
		int size = alphabet.size();
		if (cnt == 0) {
			code += alphabet.get(cnt % alphabet.size());
		}
		while (cnt > 0) {
			code += alphabet.get(cnt % alphabet.size());
			cnt /= size;
		}
		newBook.setId(code);
		
		
		// 책 정보 삽입

		Random randKey = new Random();
		newBook.setTitle("제목_" + randKey.nextInt(100) + 1);
		newBook.setContent("줄거리/설명?_" + randKey.nextInt(100) + 1);
		newBook.setWriterId("TEST_01");
		
		try {
			serviceBook.insertBook(newBook);
		} catch (DataIntegrityViolationException e) {
			System.out.println("코드값 중복");
			System.out.println(e);
		}
	}
}
