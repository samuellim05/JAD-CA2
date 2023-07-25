<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bookDetails.css">

</head>
<body>
	<%@ include file="header.html"%>

	<%@ include file="navbar.jsp"%>

	<%
	
	//get ID inputted
	String inputID = request.getParameter("bookID");
	String isbn;
	String title = "";
	String author = "";
	Double price;
	int quantity;
	String publisher = "";
	String publish_date = "";
	String category = "";
	String rating = "";
	String description = "";
	String image = "";

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
		String sqlStr = "SELECT * FROM jad.books where id=" + inputID;
		ResultSet rs = stmt.executeQuery(sqlStr);

		// Step 6: Process Result
		while (rs.next()) {
			isbn = rs.getString("ISBN");
			title = rs.getString("title");
			author = rs.getString("author");
			price = rs.getDouble("price");
			quantity = rs.getInt("quantity");
			publisher = rs.getString("publisher");
			publish_date = rs.getString("publication_date");
			category = rs.getString("genre");
			rating = rs.getString("rating");
			description = rs.getString("description");
			image = rs.getString("img");

			//display book
			out.print("<div class='container'>");
			out.print("<img class='image' src='" + image + "' alt='Image'>");
			out.print("<div class='book-details'>");
			out.print("<p class='title'>" + title + "</p>");
			out.print("<p class='author'>by " + author + "</p>");
			out.print("<div class='book-desc'>");
			out.print("<p>ISBN:" + isbn + "</p>");
			out.print("<p>Quantity:" + quantity + "</p>");
			out.print("<p>Publisher:" + publisher + "</p>");
			out.print("<p>Publication date:" + publish_date + "</p>");
			out.print("<p>Genre:" + category + "</p>");
			out.print("<p>Rating:" + rating + "</p>");
			out.print("<p>Description:" + description + "</p>");
			out.print("</div>");
			out.print("<p class='price'>Price: $" + price + "</p>");
			out.print("<a href='addToCart.jsp?Return=" + request.getRequestURI() + "?bookID=" + inputID + "&bookID="
			+ inputID + "'>Add to Cart</a>");
			out.print("</div>");
			out.print("</div>");
		}

		// Step 7: Close connection
		conn.close();
	} catch (Exception e) {
		out.println("Error :" + e);
	}
	%>

	<%@ include file="footer.html"%>
</body>
</html>