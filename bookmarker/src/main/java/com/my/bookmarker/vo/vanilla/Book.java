package com.my.bookmarker.vo.vanilla;

public class Book {
	private String id;
	private String title;
	private String content;
	private String writerId;
	private String genres;
	private String writer;
	
	public String toString() {
		return "{\n"
				+ "\tid : " + id + "\n"
				+ "\ttitle : " + title + "\n"
				+ "\tcontent : " + content + "\n"
				+ "\twriter : " + writer + "\n"
				+ "\tgenres : " + genres + "\n"
				+ "\twriterId : " + writerId + "\n"
				+ "}";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
}
