package com.my.bookmarker.service;

import java.util.List;

import com.my.bookmarker.vo.vanilla.Book;

public interface CrawlerService {
	public List<Book> crawlBookInfo(int cntBook);
	public List<Book> crawlBookInfoByGenre(int cntBook);
}
