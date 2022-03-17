package com.libraryManagement.web.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Librarian {
	
	@Id
	private String username;	
	private String password;
	
	
	public Librarian() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
	
}
