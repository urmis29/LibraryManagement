package com.libraryManagement.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libraryManagement.web.model.Librarian;
import com.libraryManagement.web.repository.UserRepository;
import com.libraryManagement.web.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public boolean validateUser(String username, String password) {
		
		List<Librarian> users = userRepository.findAll();
		for(Librarian user: users) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}

	
	
}
