package com.libraryManagement.web.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


@Entity
public class Book {
	
	@Id
	private int bookCode;
	private String bookName;
	private String author;
//	private LocalDateTime updatedOn;
	private String updatedOn;
	
	
	
	public Book() {
		super();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		LocalDateTime now = LocalDateTime.now();
		updatedOn = dtf.format(now);
	}
	public int getBookCode() {
		return bookCode;
	}
	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	
//	public LocalDateTime getUpdatedOn() {
//		return updatedOn;
//	}
//	public void setUpdatedOn(LocalDateTime updatedOn) {
//		this.updatedOn = updatedOn;
//	}
	
	
//	@PrePersist
//    public void prePersist() {
//		updatedOn = LocalDateTime.now();
//    }
// 
//    @PreUpdate
//    public void preUpdate() {
//        updatedOn = LocalDateTime.now();
//    }
	
	
}
