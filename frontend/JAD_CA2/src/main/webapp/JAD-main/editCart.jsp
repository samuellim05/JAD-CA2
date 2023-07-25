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
<title>Insert title here</title>
</head>
<body>
	<%
	String action = request.getParameter("action");
	String title = request.getParameter("title");
	String role = (String) session.getAttribute("sessUserRole");

	//if role is member, connect to cart database
	if (role != null && role.equals("member")) {
		int memberId = (int) session.getAttribute("sessUserID");
		String sQuantity = request.getParameter("quantity");
		int iQuantity = 0;

		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");

			// Step 2: Define Connection URL
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			if (sQuantity != null && sQuantity != "") {
				iQuantity = Integer.parseInt(sQuantity);
				String sBookID = request.getParameter("bookID");
				int iBookID = Integer.parseInt(sBookID);
				
				if (action.equals("add")) {

		//sql command to edit the quantity of duplicate books in the cart table
		String sqlStr = "UPDATE jad.cart SET quantity = ? WHERE member_id = ? and book_id = ?";
		//prepare statement
		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		//set the values
		pstmt.setInt(2, memberId);
		pstmt.setInt(3, iBookID);
		pstmt.setInt(1, iQuantity + 1);
		int count = pstmt.executeUpdate();
		// Step 6: Process Result
		if (count > 0)
			System.out.println(count + " records inserted");

		// Step 7: Close connection
		conn.close();
			} else if (iQuantity <= 1) {
		//delete book if quantity of book is less than 1
		String delStr = "DELETE FROM jad.cart WHERE member_id = ? and book_id = ?;";
		//prepare statement
		PreparedStatement dPstmt = conn.prepareStatement(delStr);
		//set the values
		dPstmt.setInt(1, memberId);
		dPstmt.setInt(2, iBookID);
		int count = dPstmt.executeUpdate();
		// Step 6: Process Result
		if (count > 0)
			System.out.println(count + " records inserted");

		// Step 7: Close connection
		conn.close();

			} else if (action.equals("minus")) {
		
		//sql command 
		String sqlStr = "UPDATE jad.cart SET quantity = ? WHERE member_id = ? and book_id = ?";
		//prepare statement
		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		//set the values
		pstmt.setInt(2, memberId);
		pstmt.setInt(3, iBookID);
		//the quantity of the duplicate book is decreased by 1
		pstmt.setInt(1, iQuantity - 1);
		int count = pstmt.executeUpdate();
		// Step 6: Process Result
		if (count > 0)
			System.out.println(count + " records inserted");

		// Step 7: Close connection
		conn.close();
			} else if (action.equals("delete")) {
		//delete book if the user pressed delete
		String delStr = "DELETE FROM jad.cart WHERE member_id = ? and book_id = ?;";
		//prepare statement
		PreparedStatement dPstmt = conn.prepareStatement(delStr);
		//set the values
		dPstmt.setInt(1, memberId);
		dPstmt.setInt(2, iBookID);
		int count = dPstmt.executeUpdate();
		// Step 6: Process Result
		if (count > 0)
			System.out.println(count + " records inserted");

		// Step 7: Close connection
		conn.close();
			} 
			}else if (action.equals("clear")) {
		//clear all the items in the cart
		String delStr = "DELETE FROM jad.cart WHERE member_id = ? ";
		//prepare statement
		PreparedStatement dPstmt = conn.prepareStatement(delStr);
		//set the values
		dPstmt.setInt(1, memberId);
		int count = dPstmt.executeUpdate();
		// Step 6: Process Result
		if (count > 0)
			System.out.println(count + " records inserted");

		// Step 7: Close connection
		conn.close();

			}

		} catch (Exception e) {
			System.out.println("Error :" + e);
		}

	} else {
		//if user is a guest, retrieve the cart details from cookies
		Cart cart = new Cart();
		ArrayList<Cart> bookCart = (ArrayList<Cart>) session.getAttribute("bookCart");

		
		for (int i = 0; i < bookCart.size(); i++) {
			Cart book = bookCart.get(i);
			//user pressed + button, the quantity increases by 1
			if (action.equals("add")) {
		if (book.getTitle().equals(title)) {
			book.setQuantityInCart(book.getQuantityInCart() + 1);
		}
			} else {
				//if book in cart is less than one, the book in the cart will be removed
		if (book.getQuantityInCart() <= 1) {
			if (book.getTitle().equals(title)) {
				bookCart.remove(book);
			}
			// if user pressed - button, the duplicate book quantity will decrease by 1
		} else if (action.equals("minus")) {
			if (book.getTitle().equals(title)) {
				book.setQuantityInCart(book.getQuantityInCart() - 1);
			}
			// if user pressed delete, the book will be removed
		} else if (action.equals("delete")) {
			if (book.getTitle().equals(title)) {
				bookCart.remove(book);
			}
			// if user pressed clear, all the books will be removed
		} else if (action.equals("clear")) {

			bookCart.clear();

		}
			}
		}
	}

	response.sendRedirect("displayCart.jsp");
	%>
</body>
</html>