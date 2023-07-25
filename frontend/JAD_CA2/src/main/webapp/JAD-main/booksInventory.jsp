<!--
	-Name: Samuel Lim Zhi En
	-Class: DIT/2A/02
	-Admission no. 2227874
  -->

<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/table.css">
<meta charset="ISO-8859-1">
<title>Bookstore Inventory</title>
</head>

<body>
	<%
	//session management to prevent public from accessing
	String user = request.getParameter("name");
	String role = (String) session.getAttribute("sessUserRole");
	if (role == null||role.equals("member")||!role.equals("admin")) {
		response.sendRedirect("denyAccess.jsp");
	}
	%>
	<%@ include file="header.html"%>
	<%@ include file="adminNav.html"%>
	<br>
	<div class="container1">
	<!-- Search filters -->
	<form action="" method="get" class="search">
		<!-- search book by title -->
		<label>Search Book:</label><input type="text" placeholder="Enter Book Title" name="search" class="search-box">
		<!-- filter books by genre -->
		<label>Filter book by genre:</label><select name="genre" class="search-box">
								<option value="All">All</option>
								<option value="fantasy">Fantasy</option>
								<option value="horror">Horror</option>	
								<option value="manga">Manga</option>
								<option value="mystery">Mystery</option>
								<option value="romance">Romance</option>	
								<option value="sci-fi">Sci-Fi</option>	
							</select>
							<input type='submit' name='btnSubmit' value='Search' class="search-box">
	</form>
	</div>
	
	<h1 class="center">Bookstore Inventory</h1>
	<h2>Welcome back</h2>
	<%
	int id = 0;
	String img = "";
	String genre = request.getParameter("genre");
	//filter by genre-All
	if (genre == null ||genre.equals("All")) {
	    genre = ""; 
	   
	}
	//if search book title input is empty, all books will be displayed
	String title = request.getParameter("search");
	if (title == null || title.isEmpty()) {
	    title = ""; 
	}
	//initialisation of variables
	String author = "";
	double price = 0.0;
	double rating =0.0;
	int quantity = 0;
	//create sql conn
	try {
		// Step1: Load JDBC Driver
		Class.forName("com.mysql.jdbc.Driver");

		// Step 2: Define Connection URL
		String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

		// Step 3: Establish connection to URL
		Connection conn = DriverManager.getConnection(connURL);
		// Step 4: Create Statement object
		Statement stmt = conn.createStatement();
		// Step 5: Execute SQL Command
		String sqlStr = "SELECT * FROM jad.books WHERE title LIKE ? AND genre LIKE ?;";
		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		pstmt.setString(1, "%" + title + "%");
		pstmt.setString(2, "%" + genre + "%");
		ResultSet rs = pstmt.executeQuery();
	%>
	<div class="container2">
	<table border='1' class="center">
		<tr>
			<th>ID</th>
			<th>Cover</th>
			<th>Title</th>
			<th>Genre</th>
			<th>Author</th>
			<th>Rating</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Action</th>
		</tr>

		<%
		// Step 6: Process Result
		while (rs.next()) {
			img = rs.getString("img");
			id = rs.getInt("id");
			title = rs.getString("title");
			genre = rs.getString("genre");
			author = rs.getString("author");
			price = rs.getDouble("price");
			rating = rs.getDouble("rating");
			quantity = rs.getInt("quantity");
		%>
		
		<tr>
			<td><%=id%></td>
			<td><img src="<%=img %>" alt="book Cover" width="70" height="70"></td>
			<td><%=title%></td>
			<td><%=genre%></td>
			<td><%=author%></td>
			<td><%=rating %></td>
			<td>$<%=price%></td>
			<td><%=quantity%></td>
			<td><a href='editBookForm.jsp?id=<%=id%>'><button>edit</button></a>
			<a href="<%=request.getContextPath() %>/DeleteServlet?id=<%=id%>"><button>delete</button></a></td>
		</tr>
		<%
		}

		// Step 7: Close connection
		conn.close();
		} catch (Exception e) {
		out.println("Error :" + e);
		}
	
		%>


	</table>
	</div>
	

	<%@ include file="footer.html"%>
</body>
</html>