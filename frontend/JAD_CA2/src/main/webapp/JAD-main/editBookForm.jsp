<!--
	-Name: Samuel Lim Zhi En
	-Class: DIT/2A/02
	-Admission no. 2227874
  -->

<%@page import ="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/table.css">
<meta charset="ISO-8859-1">
<title>Edit Book Details</title>
</head>
<body>
<%
//session management to prevent public from accessing
	String user = request.getParameter("name");
	String role = (String) session.getAttribute("sessUserRole");
	if (role.equals("member")||role==null||role=="") {
		response.sendRedirect("denyAccess.jsp");
	}
	%>
<%@ include file="adminNav.html"%>
<%
String id = request.getParameter("id");
String img = "";
String title = "";
String author = "";
double price = 0.0;
int quantity = 0;
String publisher = "";
String publication_date = "";
String isbn = "";
String genre = "";
double rating = 0.0;
String description = "";
String bookCover = "";
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
    String sqlStr = "SELECT * FROM jad.books WHERE id=?;";         
    PreparedStatement pstmt= conn.prepareStatement(sqlStr);
    pstmt.setString(1, id);
    System.out.print(sqlStr);
    ResultSet rs = pstmt.executeQuery();

    // Step 6: Process Result
    while (rs.next()) {
    	//get all the details from database
        id = rs.getString("id");
        title = rs.getString("title");
        author= rs.getString("author");
        price = rs.getDouble("price");
        quantity = rs.getInt("quantity");
        publisher = rs.getString("publisher");
        publication_date = rs.getString("publication_date");
        isbn = rs.getString("ISBN");
        genre = rs.getString("genre");
        rating = rs.getDouble("rating");
        description = rs.getString("description");
        bookCover = rs.getString("img");
    }
%>
	<h1>EDIT BOOK DETAILS</h1>
	<div class="container2">
	<form action="<%=request.getContextPath() %>/UpdateServlet" method="post" class="addBookForm">
		<table border='1'>
			<%--Display ID--%>
			<tr>
				<td>id:</td>
				<td><input name="eID" value=<%=id %> readonly></td>
			</tr>
			<%--Display title--%>
			<tr>
				<td>title:</td>
				<td><input name="eTitle" value=<%=title %> required></td>
			</tr>
			<%--Display author--%>
			<tr>
				<td>Author:</td>
				<td><input name="eAuthor" value=<%=author %> required></td>
			</tr>
			<%--edit the price of the book --%>
			<tr>
				<td>Price:</td>
				<td><input name="ePrice" type="number" step="0.01" min="0" value=<%=price %> required></td>
			</tr>
			<%-- edit the quantity of the book --%>
			<tr>
				<td>Quantity:</td>
				<td><input type="number" min="0" name="eQuantity" value=<%=quantity %> required></td>
			</tr>
			<%--display the publisher of the book--%>
			<tr>
				<td>Publisher:</td>
				<td><input name="ePublisher" value=<%=publisher %> required></td>
			</tr>
			<%--display the publication_date of the book--%>
			<tr>
				<td>Publication Date:</td>
				<td><input name="ePublicationDate" value=<%=publication_date %> required></td>
			</tr>
			<%--display the isbn of the book--%>
			<tr>
				<td>ISBN:</td>
				<td><input name="eIsbn" value=<%=isbn %> required></td>
			</tr>
			<%--display the genre of the book--%>
			<tr>
				<td>Genre:</td>
				<td><input name="eGenre" value=<%=genre %> required></td>
			</tr>
			<%--display the rating of the book (min value 0 max value 5)--%>
			<tr>
				<td>Rating:</td>
				<td><input name="eRating" type="number" step="0.01" min="0" max="5" value=<%=rating %> required></td>
			</tr>
			<%--display the description of the book--%>
			<tr>
				<td>Description:</td>
				<td><input name="eDescription" value=<%=description %> required></td>
			</tr>
			<!-- book cover image is not required as there will be a default image in the database -->
			<tr>
				<td>Book Cover URL:</td>
				<td><input name="eImg" value=<%=bookCover %>></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input type='submit' name='btnSubmit' value='Submit' class="btn"></td>
			<tr>
		</table>
	</form>
	</div>
	
<%
    // Step 7: Close connection
    conn.close();
} catch (Exception e) {
  out.println("Error :" + e);
}
%>
</body>
</html>