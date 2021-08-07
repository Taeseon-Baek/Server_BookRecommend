package com.my.bookmarker.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.bookmarker.service.BookService;
import com.my.bookmarker.vo.vanilla.Book;

@RestController
@RequestMapping(value = "/insert")
public class InsertController {
	
	@Autowired
	private BookService serviceBook;
	
	@RequestMapping(value = "/book", method=RequestMethod.GET)
	public void insertBookTest() {
		Book newBook = new Book();
		Random randKey = new Random();
		newBook.setId("CODE_" + randKey.nextInt(100) + 1);
		newBook.setTitle("����_" + randKey.nextInt(100) + 1);
		newBook.setContent("�ٰŸ�/����?_" + randKey.nextInt(100) + 1);
		newBook.setWriterId("TEST_01");
		
		try {
			serviceBook.insertBook(newBook);
		} catch (DataIntegrityViolationException e) {
			System.out.println("�ڵ尪 �ߺ�");
			System.out.println(e);
		}
	}
}
