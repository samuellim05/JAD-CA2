<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
</head>
<body>

<%
    // Invalidate the session
    session.invalidate();

    // Redirect the user to the login page
    response.sendRedirect("login.jsp");
%>

</body>
</html>