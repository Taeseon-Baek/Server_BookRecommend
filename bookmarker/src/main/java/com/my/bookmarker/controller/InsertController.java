package com.my.bookmarker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.bookmarker.service.BookService;
import com.my.bookmarker.service.CrawlerService;
import com.my.bookmarker.service.GenreService;
import com.my.bookmarker.service.WriterService;
import com.my.bookmarker.vo.generator.Code;
import com.my.bookmarker.vo.vanilla.Book;
import com.my.bookmarker.vo.vanilla.Genre;
import com.my.bookmarker.vo.vanilla.Writer;

@RestController
@RequestMapping(value = "/insert")
public class InsertController {
	
	@Autowired
	private BookService serviceBook;
	@Autowired
	private WriterService serviceWriter;
	@Autowired
	private GenreService serviceGenre;
	@Autowired
	private CrawlerService serviceCrawler;
	
	private ArrayList<String> alphabet = 
			new ArrayList<String>(Arrays.asList(
					"a", "b", "c", "d", "e", "f", 
					"g", "h", "i", "j", "k", "l", 
					"m", "n", "o", "p", "q", "r", 
					"s", "t", "u", "v", "w", "x", 
					"y", "z" 
					)); 
	
	@RequestMapping(value = "/writer/{name}", method = RequestMethod.GET)
	public Writer test(@PathVariable String name) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		List<Writer> temp = serviceWriter.selectWriter(param);
		Writer result = new Writer();
		if(temp.size() != 0) {
			result = temp.get(0);	
		}
		return result;
	}
	
	@RequestMapping(value = "/crawling/yp/{cntBook}", method=RequestMethod.GET)
	public void insertBookTest(@PathVariable int cntBook) {
		List<Book> books = serviceCrawler.crawlBookInfo(cntBook);
		
		for (Book book : books) {
			// 기존의 같은 제목 책이 존재 ? 추가 안함 : 추가함
			if (serviceBook.selectBook(book.getTitle()).size() == 0) {
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
				// 책 코드 삽입
				book.setId(code);
				
				// 저자 검색 -> 저자 존재 ? 존재하는 저자 호출 : 저자 생성
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("name", book.getWriter());
				List<Writer> writerCheck = serviceWriter.selectWriter(param);
				Writer writerInfo = new Writer();
				
				if (writerCheck.size() != 0) {
					writerInfo = writerCheck.get(0);
				} else {
					codeForm = serviceWriter.selectWriterId().get(0);
					code = "W_";
					cnt = codeForm.getCnt();
					if (cnt < 10) {
						code += "000" + cnt;
					} else if (cnt < 100) {
						code += "00" + cnt;
					} else if (cnt < 1000) {
						code += "0" + cnt;
					} else if (cnt < 10000) {
						code += "" + cnt;
					} 
					writerInfo.setName(book.getWriter());
					writerInfo.setId(code);
					writerInfo.setFrequency(1);
					serviceWriter.insertWriter(writerInfo);
				}
				// 저자 코드 삽입
				book.setWriterId(writerInfo.getId());

				String genresInsert = "";
				for (String genre : book.getGenres().split("/")) {
					genresInsert += genre + "/";
					// 장르 검색 -> 장르 존재 ? 존재하는 장르 호출 : 장르 생성
					param = new HashMap<String, Object>();
					param.put("name", genre);
					List<Genre> genreCheck = serviceGenre.selectGenre(param);
					Genre genreInfo = new Genre();
					if (genreCheck.size() != 0) {
						genreInfo = genreCheck.get(0);
					} else {
						codeForm = serviceGenre.generateGenreId().get(0);
						code = "G_";
						cnt = codeForm.getCnt();
						if (cnt < 10) {
							code += "000" + cnt;
						} else if (cnt < 100) {
							code += "00" + cnt;
						} else if (cnt < 1000) {
							code += "0" + cnt;
						} else if (cnt < 10000) {
							code += "" + cnt;
						}
						genreInfo.setId(code);
						genreInfo.setName(genre);
						serviceGenre.insertGenre(genreInfo);
					}
					
					// 장르 ~ 책 연관성 삽입
					book.setGenres(genresInsert);

					try {
						serviceBook.insertBook(book);
					} catch (DataIntegrityViolationException e) {
						System.out.println("코드값 중복");
						System.out.println(e);
					}
					System.out.println("책 신규 삽입: " + book.toString());
				}
			} else {
				System.out.println("같은 제목의 책 존재");
			}
		}
	}
}
