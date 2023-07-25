/* 
	-Name: Samuel Lim Zhi En
	-Class: DIT/2A/02
	-Admission no. 2227874
*/

package assignment;
import java.sql.*;

public class Book {
	private String Title; 
	private String Author; 
	private double Price;
	private int Quantity; 
	private String Publisher; 
	private String publication_date;
	private String ISBN; 
	private String genre;
	private String rating;
	private String description;
	private String img;
	
	public Book(String title, String author, double price, int quantity, String publisher, String publication_date,
			String iSBN, String genre, String rating, String description, String img) {
		super();
		Title = title;
		Author = author;
		Price = price;
		Quantity = quantity;
		Publisher = publisher;
		this.publication_date = publication_date;
		ISBN = iSBN;
		this.genre = genre;
		this.rating = rating;
		this.description = description;
		this.img = img;
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public String getPublication_date() {
		return publication_date;
	}
	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	//create book record method
	public void createBook() {
		try {
	          // Step1: Load JDBC Driver
	           Class.forName("com.mysql.jdbc.Driver");  

	          // Step 2: Define Connection URL
	          String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

	          // Step 3: Establish connection to URL
	          Connection conn = DriverManager.getConnection(connURL); 
	          //sql command
	          String insertStr = "INSERT INTO jad.books (title, author, price, quantity, publisher, publication_date, ISBN, genre, rating, description, img) VALUES (?,?,?,?,?,?,?,?,?,?,?)"; 
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(insertStr);
	          //set the values
	          pstmt.setString(1, Title);
	          pstmt.setString(2, Author);
	          pstmt.setDouble(3, Price);
	          pstmt.setInt(4, Quantity);
	          pstmt.setString(5, Publisher);
	          pstmt.setString(6, publication_date);
	          pstmt.setString(7, ISBN);
	          pstmt.setString(8, genre);
	          pstmt.setString(9, rating);
	          pstmt.setString(10, description);
	          pstmt.setString(11, img);
	          int count = pstmt.executeUpdate();
	          // Step 6: Process Result
	          if (count > 0) System.out.println (count + " records inserted");

	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
	}
	
	public void updateBook(int id) {
		try {
	          // Step1: Load JDBC Driver
	           Class.forName("com.mysql.jdbc.Driver");  

	          // Step 2: Define Connection URL
	          String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

	          // Step 3: Establish connection to URL
	          Connection conn = DriverManager.getConnection(connURL); 
	          //sql command
	          String updateStr = "UPDATE jad.books set title=?, author=?, price=?, quantity=?, publisher=?, publication_date=?, ISBN=?, genre=?, rating=?, description=?, img=? WHERE id=?;";
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(updateStr);
	          //set the values
	          pstmt.setString(1, Title);
	          pstmt.setString(2, Author);
	          pstmt.setDouble(3, Price);
	          pstmt.setInt(4, Quantity);
	          pstmt.setString(5, Publisher);
	          pstmt.setString(6, publication_date);
	          pstmt.setString(7, ISBN);
	          pstmt.setString(8, genre);
	          pstmt.setString(9, rating);
	          pstmt.setString(10, description);
	          pstmt.setString(11, img);
	          pstmt.setInt(12, id);
	          int count = pstmt.executeUpdate();
	          // Step 6: Process Result
	          if (count > 0) 
	        	  System.out.println (count + " records inserted");

	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
	}
	
	public void deleteBook (int id) {
		try {
	          // Step1: Load JDBC Driver
	           Class.forName("com.mysql.jdbc.Driver");  

	          // Step 2: Define Connection URL
	          String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

	          // Step 3: Establish connection to URL
	          Connection conn = DriverManager.getConnection(connURL); 
	          //sql command
	          String deleteStr = "DELETE FROM jad.books WHERE id= ?"; 
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(deleteStr);
	          //set the values
	          pstmt.setInt(1, id);
	          int count = pstmt.executeUpdate();
	          // Step 6: Process Result
	          if (count > 0) System.out.println (count + " records deleted");

	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
	}
	
	public void add2Cart (int id) {
		
	}
}
