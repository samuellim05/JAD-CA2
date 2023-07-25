<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Browse</title>
<link rel="stylesheet" href="css/browse.css">

</head>
<body>

	<%@ include file="navbar.jsp"%>
<form method="post" action="" class="search-form">
<table>

    <tr>
        <td><label for="titleInput">Title:</label></td>
        <td><input type="text" class="search-input" id="titleInput" name="titleInput" placeholder="Enter title here"></td>
    </tr>
    <tr>
        <td><label for="authorInput">Author:</label></td>
        <td><input type="text" class="search-input" id="authorInput" name="authorInput" placeholder="Enter author here"></td>
    </tr>
    <tr>
    <td colspan="2"><button type="submit" class="search-btn">Search</button></td>
    </tr>

</table>
</form>
 


	<%
	// Check if the form has been submitted
	String titleInput = "";
	String authorInput = "";
	if (request.getMethod().equalsIgnoreCase("post")) { //checks for a POST request
	    // Retrieve the submitted inputs from the form
	    titleInput = request.getParameter("titleInput");
	    authorInput = request.getParameter("authorInput");
	}

	// Display the submitted inputs
	//out.println("<p>Search results for title: " + titleInput + "</p></br>");  
	//out.println("<p>Search results for author: " + authorInput + "</p></br>");

	//SQL
	int id;
	String title = "";
	String author = "";
	String description = "";
	double price;
	String image = "";
	String category = "";
	
	try {
		// Step1: Load JDBC Driver
		Class.forName("com.mysql.jdbc.Driver");

		// Step 2: Define Connection URL
		String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

		// Step 3: Establish connection to URL
		Connection conn = DriverManager.getConnection(connURL);

		// Step 4: Create PreparedStatement object
		
		String sqlStr = "SELECT * FROM jad.books WHERE title LIKE CONCAT('%', ?, '%') AND author LIKE CONCAT('%', ?, '%')";
		PreparedStatement stmt = conn.prepareStatement(sqlStr);
		
		stmt.setString(1, titleInput);
		stmt.setString(2, authorInput);
	
	
		// Step 5: Execute SQL Command

		ResultSet rs = stmt.executeQuery();

		// Step 6: Process Result
		while (rs.next()) {
		id = rs.getInt("id");
		title = rs.getString("title");
		author = rs.getString("author");
		description = rs.getString("description");
		price = rs.getDouble("price");
		image = rs.getString("img");
		category = rs.getString("genre");
		
		//display books
		out.print("<div class='container'>");
		out.print("<div class='row'>");
		out.print("<img class='image' src='" + image + "' alt='Image'>");
		out.print("<div class='text'>");
		out.print("<h1>" + title + "</h1>");
		out.print("<br>Author: <div class='price'>" + author + "</div>");
		out.print("<br>Category: <div class='price'>" + category + "</div>");
		out.print("<br>Description:<div class='price'>" + description + "</div>");
		out.print("<div class='price'><p>Price: $" + price + "</p></div>");
		out.print("<a href='bookDetails.jsp?bookID=" + id + "'>View Details</a>");
		out.print("<a href='addToCart.jsp?Return=" + request.getRequestURI() + "&bookID=" + id + "'>Add to Cart</a>");
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


	<br>
	<%@ include file="footer.html"%>

</body>
</html>