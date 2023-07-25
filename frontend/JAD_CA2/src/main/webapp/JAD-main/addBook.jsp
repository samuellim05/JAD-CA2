<!--
	-Name: Samuel Lim Zhi En
	-Class: DIT/2A/02
	-Admission no. 2227874
  -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/table.css">
<meta charset="ISO-8859-1">
<title>Add Book</title>
</head>
<body>
<%@ include file="adminNav.html"%>
	<%
	//session management to prevent public from accessing
	String user = request.getParameter("name");
	String role = (String) session.getAttribute("sessUserRole");
	if (role == null||role.equals("member")||!role.equals("admin")) {
		response.sendRedirect("denyAccess.jsp");
	}
	%>
	<h1>Add Book</h1>
	<!-- Form table to get the details for adding a new book -->
	<form action="<%=request.getContextPath() %>/BookServlet" method="post" class="addBookForm">
		<table border='1'>
			<%--Display title--%>
			<tr>
				<td>title:</td>
				<td><input type="text" name="Title" required></td>
			</tr>
			<%--Display author (readonly) --%>
			<tr>
				<td>Author:</td>
				<td><input type="text" name="Author" required></td>
			</tr>
			<%--edit the price of the book --%>
			<tr>
				<td>Price:</td>
				<td><input type="number" step="0.01" min="0" name="Price" required></td>
			</tr>
			<%-- edit the quantity of the book --%>
			<tr>
				<td>Quantity:</td>
				<td><input type="number" min="0" name="Quantity" required></td>
			</tr>
			<%--display the publisher of the book (readonly) --%>
			<tr>
				<td>Publisher:</td>
				<td><input type="text" name="Publisher" required></td>
			</tr>
			<%--display the publication_date of the book (readonly) --%>
			<tr>
				<td>Publication Date:</td>
				<td><input type="datetime" name="PublicationDate" required></td>
			</tr>
			<%--display the publisher of the book (readonly) --%>
			<tr>
				<td>ISBN:</td>
				<td><input type="text" name="Isbn" required></td>
			</tr>
			<%--display the publisher of the book (readonly) --%>
			<tr>
				<td>Genre:</td>
				<%--<td><input type="text" name="Genre"></td> --%>
				<td><select name="genre" required>
						<option value="Horror" selected>Horror</option>
						<option value="Sci-Fi">Sci-Fi</option>
						<option value="Romance">Romance</option>
						<option value="Manga">Manga</option>
						<option value="Fantasy">Fantasy</option>
						<option value="Mystery">Mystery</option>
				</select></td>
			</tr>
			<%--display the publisher of the book (readonly) --%>
			<tr>
				<td>Rating:</td>
				<td><input type="number" step="0.01" min="0" max="5" name="Rating" required></td>
			</tr>
			<%--display the publisher of the book (readonly) --%>
			<tr>
				<td>Description:</td>
				<td><input type="text" name="Description" required></td>
			</tr>
			<tr>
				<td>Book Cover URL:</td>
				<td><input type="text" name="Img"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input type='submit' name='btnSubmit' value='Submit'></td>
			<tr>
		</table>
	</form>
	<!-- error message -->
	<%
	String err = request.getParameter("errCode");
    if (err != null && err.equals("Please fill in all the inputs")) {
        out.println("Invalid login");
    }
	%>
	
</body>
</html>