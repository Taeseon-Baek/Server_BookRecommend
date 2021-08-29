package com.my.bookmarker.service;

import com.my.bookmarker.vo.vanilla.Book;
import com.my.bookmarker.vo.vanilla.Genre;
import com.my.bookmarker.vo.vanilla.Writer;

public interface DeleteService {
	
	public void deleteBook(Book item);
	public void deleteGenre(Genre item);
	public void deleteWriter(Writer item);

}
