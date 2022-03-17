package com.libraryManagement.web.service;

import java.util.List;

import com.libraryManagement.web.model.Book;

public interface BookService {
	
	List<Book> getAllBooks();
	Book getBook(int bookCode);
	void addBook(int bookCode, String bookName, String author);
	void updateBook(int bookCode, String newBookName, String newAuthor);
	void deleteBook(int bookCode);
}
