package com.libraryManagement.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryManagement.web.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
