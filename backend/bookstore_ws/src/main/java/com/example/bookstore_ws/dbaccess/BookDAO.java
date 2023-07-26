package com.example.bookstore_ws.dbaccess;
import java.sql.*;
import java.util.List;


import com.example.bookstore_ws.dbaccess.Book;

import java.util.ArrayList;


public class BookDAO {

	public Book getBookById(String bookId) throws SQLException{
		Connection conn = null;
		Book book = null;
		try {
			conn = DBConnection.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM jad.books where id = ?");
			pstmt.setString(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				book = new Book();
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getDouble("price"));
				book.setQuantity(rs.getInt("quantity"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublication_date(rs.getString("publication_date"));
				book.setISBN(rs.getString("ISBN"));
				book.setRating(rs.getString("rating"));
				book.setDescription(rs.getString("description"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return book;
	}
	
	public ArrayList<Book> getAllBooks() throws SQLException{
		ArrayList<Book> bookList = new ArrayList<Book>();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();

			Statement stmt = conn.createStatement();
			String sqlStr = "SELECT * FROM jad.books";
			ResultSet rs = stmt.executeQuery(sqlStr);
			
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getDouble("price"));
				book.setQuantity(rs.getInt("quantity"));
				book.setGenre(rs.getString("genre"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublication_date(rs.getString("publication_date"));
				book.setISBN(rs.getString("ISBN"));
				book.setRating(rs.getString("rating"));
				book.setDescription(rs.getString("description"));
				book.setImg(rs.getString("img"));
				bookList.add(book);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public int createBook(Book book) {
		Connection conn = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();

			//sql command
	          String insertStr = "INSERT INTO jad.books (title, author, price, quantity, publisher, publication_date, ISBN, genre, rating, description, img) VALUES (?,?,?,?,?,?,?,?,?,?,?)"; 
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(insertStr);
	          //set the values
	          pstmt.setString(1, book.getTitle());
				pstmt.setString(2, book.getAuthor());
				pstmt.setDouble(3, book.getPrice());
				pstmt.setInt(4, book.getQuantity());
				pstmt.setString(5, book.getPublisher());
				pstmt.setString(6, book.getPublication_date());
				pstmt.setString(7, book.getISBN());
				pstmt.setString(8, book.getGenre());
				pstmt.setString(9, book.getRating());
				pstmt.setString(10, book.getDescription());
				pstmt.setString(11, book.getImg());

	          count = pstmt.executeUpdate();
	          // Step 6: Process Result
	          if (count > 0) System.out.println (count + " records inserted");

	          // Step 7: Close connection
	          conn.close();
		} catch (SQLException e) {
			// Print out any exceptions that might be thrown
			e.printStackTrace();
		}

		return count;
	}
	
	public int updateBook(Book book, String id) {
		Connection conn = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			

			//sql command
	          String updateStr = "UPDATE jad.books set title=?, author=?, price=?, quantity=?, publisher=?, publication_date=?, ISBN=?, genre=?, rating=?, description=?, img=? WHERE id=?;";
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(updateStr);
	          //set the values
	          System.out.println(book.getRating());
	          pstmt.setString(1, book.getTitle());
				pstmt.setString(2, book.getAuthor());
				pstmt.setDouble(3, book.getPrice());
				pstmt.setInt(4, book.getQuantity());
				pstmt.setString(5, book.getPublisher());
				pstmt.setString(6, book.getPublication_date());
				pstmt.setString(7, book.getISBN());
				pstmt.setString(8, book.getGenre());
				pstmt.setString(9, book.getRating());
				pstmt.setString(10, book.getDescription());
				pstmt.setString(11, book.getImg());
	          pstmt.setString(12, id);
	          count = pstmt.executeUpdate();
	          // Step 6: Process Result
	          if (count > 0) 
	        	  System.out.println (count + " records inserted");

	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
		return count;
	}
	
	public int deleteBook(String id) {
		Connection conn = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
	          //sql command
	          String deleteStr = "DELETE FROM jad.books WHERE id= ?"; 
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(deleteStr);
	          //set the values
	          pstmt.setString(1, id);
	          count = pstmt.executeUpdate();
	          // Step 6: Process Result
	          if (count > 0) System.out.println (count + " records deleted");

	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
		return count;
	}

}
