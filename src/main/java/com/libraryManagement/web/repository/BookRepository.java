package com.libraryManagement.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryManagement.web.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
}
