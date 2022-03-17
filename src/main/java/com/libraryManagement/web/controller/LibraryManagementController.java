package com.libraryManagement.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.libraryManagement.web.exception.ResourceNotFoundException;
import com.libraryManagement.web.model.Author;
import com.libraryManagement.web.model.Book;
import com.libraryManagement.web.repository.AuthorRepository;
import com.libraryManagement.web.service.BookService;
import com.libraryManagement.web.service.UserService;

@Controller
public class LibraryManagementController {
	

	private UserService userService;
	private BookService bookService;
	private AuthorRepository authorRepository;
	ModelAndView mv;
	
	@Autowired
	public LibraryManagementController(UserService userService, BookService bookService, AuthorRepository authorRepository) {
		super();
		this.userService = userService;
		this.bookService = bookService;
		this.authorRepository = authorRepository;
		this.mv = new ModelAndView();
	}
	
	@GetMapping("/")
	public ModelAndView getLogin() {
				
		mv.setViewName("index");
		
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView validateLibrarian(String username, String password, HttpSession session) {
		
		if(userService.validateUser(username, password)) {
			
			session.setAttribute("user", username);
			mv.addObject("user", username);
			mv.setViewName("redirect:/books");
		}
		else {
			
			String msg = String.format("Librarian is not exist with Username : %s", username);
			throw new ResourceNotFoundException(msg);
		}
		
		
		return mv;
	}
	
	@PostMapping("/logout")
	public ModelAndView getLogout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		mv.setViewName("redirect:/");
		
		return mv;
	}
	
	// Handler method to list books and return view
	@GetMapping("/books")
	public ModelAndView listAllBooks() {
		
		mv.addObject("books", bookService.getAllBooks());
		mv.setViewName("bookManagement");
		
		return mv;
	}
	
	@RequestMapping("/new")
	public ModelAndView createBookForm() {
		
		List<Author> authors = authorRepository.findAll();
		mv.addObject("authors", authors);
		
		mv.setViewName("createBookForm");
		
		return mv;
		
	}
	
	@PostMapping("/add")
	public ModelAndView saveBook(int bookCode, String bookName, String author) {
			
		bookService.addBook(bookCode, bookName, author);
		
		mv.setViewName("redirect:/books");
		
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView createEditForm(int bookCode) {
				
		// Fetching a list of all authors from the database
		List<Author> authors = authorRepository.findAll();
		
		// Setting the parameters for ModelAndView object
		mv.addObject("authors", authors);
		mv.addObject("book", bookService.getBook(bookCode));
		mv.setViewName("editBookForm");
		
		return mv;
		
	}
	
	@RequestMapping("/update")
	public ModelAndView editBook(String newBookName, String newAuthor, HttpSession session) {
		
		int bookCode = (int) session.getAttribute("bookCode");
		bookService.updateBook(bookCode, newBookName, newAuthor);
		
		mv.setViewName("redirect:/books");
		
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView removeBook(int bookCode) {

		bookService.deleteBook(bookCode);
		mv.setViewName("redirect:/books");
		return mv;
	}
}
