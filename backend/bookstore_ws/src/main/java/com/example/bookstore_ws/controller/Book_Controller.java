package com.example.bookstore_ws.controller;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import assignment.Book;

import com.example.bookstore_ws.dbaccess.BookDAO;

@RestController
@RequestMapping("/ws")
public class Book_Controller {
	@GetMapping("/getBook/{bid}")
	public Book getUser(@PathVariable("bid") String bid) {
		Book book= new Book();
		try {
			BookDAO db = new BookDAO();
			book = db.getBookById(bid);
			
		} catch(Exception e) {
			System.out.println("Error:" + e);
		}
		return book;
	}
	
	@GetMapping("/getAllBooks")
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> bookList = new ArrayList<>();
		try {

			BookDAO bookDAO = new BookDAO();
			
	        bookList = bookDAO.getAllBooks();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return bookList;
	}
	
	@PostMapping("/createBook")
	public int createBook(@RequestBody Book book) {
		int rec =0;
		try {
			BookDAO bookDAO = new BookDAO();
			rec=bookDAO.createBook(book);
			System.out.print("...done create user"+rec);
		} catch(Exception e) {
			System.out.print(e.toString());
		}
		return rec;
	}
	@RequestMapping(method=RequestMethod.POST, path="/createUser")
	public String createBook() {
		return "createBook";
	}
	@PutMapping
	public String updateBook() {
		return "updateBook";
	}
	@DeleteMapping
	public String deleteBook() {
		return "deleteBook";
	}
}
