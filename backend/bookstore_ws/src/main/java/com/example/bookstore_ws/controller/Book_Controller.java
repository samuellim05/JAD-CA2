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
import com.example.bookstore_ws.dbaccess.Book;

import com.example.bookstore_ws.dbaccess.BookDAO;

@RestController
@RequestMapping("/book")

public class Book_Controller {
	@GetMapping("/getBook/{bid}")
	public Book getBook(@PathVariable("bid") String bid) {
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
			System.out.print("...done create book"+rec);
		} catch(Exception e) {
			System.out.print(e.toString());
		}
		return rec;
	}
	
	@PutMapping("/updateBook/{bid}")
	public int updateBook(@PathVariable String bid, @RequestBody Book book) {
		int rec =0;
		try {
			BookDAO db= new BookDAO(); 
			rec=db.updateBook(book, bid);
			System.out.print("...done update user"+rec);
		} catch(Exception e) {
			System.out.print(e.toString());
		}
		return rec;
	}
	
	@DeleteMapping("/deleteBook/{bid}")
	public int deleteBook(@PathVariable("bid") String bid) {
		int rec =0;
		try {
			BookDAO db= new BookDAO(); 
			rec=db.deleteBook(bid);
			System.out.print("...done delete Book"+rec);
		} catch(Exception e) {
			System.out.print(e.toString());
		}
		return rec;
	}
	
	
}
