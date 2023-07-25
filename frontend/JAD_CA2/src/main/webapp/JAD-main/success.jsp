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
<link rel="stylesheet" href="css/contactUs.css">
<meta charset="ISO-8859-1">
<title>SUCCESS</title>
</head>
<%@ include file="adminNav.html"%>
<body>
<%
//session management to prevent public from accessing
	String user = request.getParameter("name");
	String role = (String) session.getAttribute("sessUserRole");
	if (role == null||role.equals("member")||!role.equals("admin")) {
		response.sendRedirect("denyAccess.jsp");
	}
	%>
<div class="container1">

<div class="container">
<img src="img/tickSuccess.png" alt="tick" width="170" height="170">
<h1>SUCCESS!</h1>
<a href="booksInventory.jsp"><button>Home</button></a>
</div>
</div>


</body>
<%@ include file="footer.html"%>
</html>