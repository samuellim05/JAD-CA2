<!--
	-Name: Samuel Lim Zhi En
	-Class: DIT/2A/02
	-Admission no. 2227874
  -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="assignment.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add to Cart</title>
</head>
<body>
	<%
	//intitialisation of variables
		String sBookID = request.getParameter("bookID");
		int iBookID = Integer.parseInt(sBookID);
		System.out.print(iBookID);
		String img = "";
		String title = "";
		String author = "";
		double price = 0.0;
		int quantity = 0;
		String publisher = "";
		String publication_date = "";
		String isbn = "";
		String genre = "";
		String rating = "";
		String description = "";
		String bookCover = "";
		int quantityOfBook = 1;
		boolean duplicate = false;
	%>
	<%	
	//get user role from session
	
	String role= (String)session.getAttribute("sessUserRole");
	if(role != null && role.equals("member")){
		int memberId = (int) session.getAttribute("sessUserID");
		try {
	          // Step1: Load JDBC Driver
	           Class.forName("com.mysql.jdbc.Driver");  

	          // Step 2: Define Connection URL
	          String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

	          // Step 3: Establish connection to URL
	          Connection conn = DriverManager.getConnection(connURL); 
	        //sql command
				String selStr = "SELECT * FROM jad.cart where book_id = ? AND member_id=?;";
				//prepare statement
				PreparedStatement pstmt = conn.prepareStatement(selStr);
				//set the values
				pstmt.setInt(1, iBookID);
				pstmt.setInt(2, memberId);
				// Step 5: Execute SQL Command

				ResultSet rs = pstmt.executeQuery();

				// Step 6: Process Result
				if (rs.next()) {
					quantity = rs.getInt("quantity");
					//sql command
					String sqlStr = "UPDATE jad.cart SET quantity = ? WHERE member_id = ? and book_id = ?";
					//prepare statement
					PreparedStatement uPstmt = conn.prepareStatement(sqlStr);
					//set the values
					uPstmt.setInt(2, memberId);
					uPstmt.setInt(3, iBookID);
					uPstmt.setInt(1, quantity + 1);
					int count = uPstmt.executeUpdate();
					// Step 6: Process Result
					if (count > 0)
						System.out.println(count + " records inserted");

				}else{
					String insertStr = "INSERT INTO jad.cart (member_id, book_id) VALUES (?,?)"; 
			          //prepare statement
			          PreparedStatement iPstmt = conn.prepareStatement(insertStr);
			          //set the values
			          iPstmt.setInt(1, memberId);
			          iPstmt.setInt(2, iBookID);
			          int count = iPstmt.executeUpdate();
			          // Step 6: Process Result
			          if (count > 0){
			        	  System.out.println (count + " records inserted");
			          }

				}

	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
	}else{
	%>
	<%
		//if the user is a guest, use session management to store thw cart items.
	 	
	 	try {
	          // Step1: Load JDBC Driver
	           Class.forName("com.mysql.jdbc.Driver");  

	          // Step 2: Define Connection URL
	         String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

	          // Step 3: Establish connection to URL
	          Connection conn = DriverManager.getConnection(connURL); 
	          //sql command
	          String selStr = "SELECT * FROM jad.books WHERE id = ?"; 
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(selStr);
	          //set the values
	          pstmt.setInt(1, iBookID);
	       // Step 5: Execute SQL Command

	  		ResultSet rs = pstmt.executeQuery();

	  		// Step 6: Process Result
	  		while (rs.next()) {
	  	        title = rs.getString("title");
	  	        author= rs.getString("author");
	  	        price = rs.getDouble("price");
	  	        quantity = rs.getInt("quantity");
	  	        publisher = rs.getString("publisher");
	  	        publication_date = rs.getString("publication_date");
	  	        isbn = rs.getString("ISBN");
	  	        genre = rs.getString("genre");
	  	        rating = rs.getString("rating");
	  	        description = rs.getString("description");
	  	        bookCover = rs.getString("img");
	  		}
	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
	 
	// Get the existing cart from the session
	 ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("bookCart");

	 // Check if the cart is null (first time accessing the page)
	 if (cart == null) {
	     cart = new ArrayList<Cart>();
	 }
	 

		
		for (int i = 0; i < cart.size(); i++) {
			Cart book = cart.get(i);
			if(book.getTitle().equals(title)){
				duplicate = true;
				book.setQuantityInCart(book.getQuantityInCart() + 1);
			}
		}
		
		if (!duplicate) {
	        Cart book=new Cart(title, author, price, quantity, publisher, publication_date,
				isbn, genre, rating, description, bookCover, quantityOfBook);
	        cart.add(book);
	    }
		
		
		//set session attribute for the bookList
		session.setAttribute("bookCart", cart);
	
	}
	
	 	//String returnURL = request.getHeader("Return")
		String returnURL = request.getParameter("Return");
	  	response.sendRedirect(returnURL);
	  	System.out.print(returnURL);
	 	
	 %>
	

</body>
</html>