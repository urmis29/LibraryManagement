package com.libraryManagement.web.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.libraryManagement.web.exception.ResourceNotFoundException;
import com.libraryManagement.web.model.Book;
import com.libraryManagement.web.repository.BookRepository;
import com.libraryManagement.web.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}


	@Override
	public List<Book> getAllBooks() {
		
		return bookRepository.findAll();
	}


	@Override
	public Book getBook(int bookCode) {
		Optional<Book> book = bookRepository.findById(bookCode);
		if(book.isPresent()) {
			return book.get();
		}
		else {
			String msg = String.format("Book not found with Book Code : %s", bookCode);
			throw new ResourceNotFoundException(msg);
		}
		
	}
	
	
	@Override
	public void addBook(int bookCode, String bookName, String author) {
		
		boolean isExist = bookRepository.existsById(bookCode);
		
		if(!isExist) {
			Book newBook = new Book();
			newBook.setBookCode(bookCode);
			newBook.setBookName(bookName);
			newBook.setAuthor(author);
			bookRepository.save(newBook);
		}
		else {
			String msg = String.format("Book is already exist with Book Code : %s", bookCode);
			throw new ResourceNotFoundException(msg);
		}
		
	}

	
	@Override
	public void updateBook(int bookCode, String newBookName, String newAuthor) {
		
		Book newBook = new Book();
		Book book = this.getBook(bookCode);
		newBook.setBookCode(bookCode);
		newBook.setBookName(newBookName);
		newBook.setAuthor(newAuthor);
		newBook.setUpdatedOn(book.getUpdatedOn());
		
		this.deleteBook(bookCode);
		bookRepository.save(newBook);
	}
	
	@Override
	public void deleteBook(int bookCode) {
		
		bookRepository.deleteById(bookCode);
	}


	
}
