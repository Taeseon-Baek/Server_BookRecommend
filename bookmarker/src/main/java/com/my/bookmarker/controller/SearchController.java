package com.my.bookmarker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.bookmarker.service.BookService;
import com.my.bookmarker.service.GenreService;
import com.my.bookmarker.vo.vanilla.Book;

@RestController
@RequestMapping(value = "/search")
public class SearchController {
	
	@Autowired
	private BookService serviceBook;
	
	@RequestMapping(value="/writer/{name_author}", method = RequestMethod.GET)
	public List<Book> findByWriter(@PathVariable String name_author){
		return serviceBook.findByWriter(name_author);
	}
	
	@RequestMapping(value="/genre/{genres}", method = RequestMethod.GET)
	public List<Book> findByGENRE(@PathVariable String genres){
		return serviceBook.findByGenre(genres);
	}

}
