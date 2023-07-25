+<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category</title>
<link rel="stylesheet" href="css/browse.css">
    
</head>
<body>

<%@ include file="header.html"%>

<%@ include file="navbar.jsp"%>

<%
String category = request.getParameter("category");
int id;
String title = "";
String author = "";
String description = "";
double price;
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
	String sqlStr = "SELECT * FROM jad.books WHERE jad.books.genre='" + category + "'";
	ResultSet rs = stmt.executeQuery(sqlStr);

	// Step 6: Process Result
	while (rs.next()) {
		id = rs.getInt("id");
		title = rs.getString("title");
		author = rs.getString("author");
		description = rs.getString("description");
		price = rs.getDouble("price");
		image = rs.getString("img");
		
		//display books
		out.print("<div class='container'>");
		out.print("<div class='row'>");
		out.print("<img class='image' src='" + image + "' alt='Image'>");
		out.print("<div class='text'>");
		out.print("<h1>" + title + "</h1>");
		out.print("<p>Author: " + author + "</p>");
		out.print("<p>" + description + "</p>");
		out.print("<p>Category: " + category + "</p>");
		out.print("<div class='price'><p>Price: $" + price + "</p></div>");
		out.print("<a href='bookDetails.jsp?bookID=" + id + "'>View Details</a>");
		out.print("<a href='addToCart.jsp?Return=" + request.getRequestURI() + "?category=" + category + "&bookID=" + id + "'>Add to Cart</a>");
		out.print("</div>");
		out.print("</div>");
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