package com.libraryManagement.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryManagement.web.model.Librarian;

public interface UserRepository extends JpaRepository<Librarian, String> {

}
