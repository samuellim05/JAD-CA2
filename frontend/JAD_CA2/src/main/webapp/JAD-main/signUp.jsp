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
<link rel="stylesheet" href="css/contactUs.css">
<meta charset="ISO-8859-1">
<title>Sign Up Success</title>
</head>
<body>
	<%@ include file="header.html"%>
	<%
	//get the username and password which the user entered during sign up
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	boolean exist = false;
	%>
	<%
	try {
		//Load JDBC Driver
		Class.forName("com.mysql.jdbc.Driver");

		//Define Connection URL
		String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

		//Establish connection to URL
		Connection conn = DriverManager.getConnection(connURL);
		//Create Statement object
		Statement stmt = conn.createStatement();
		//Execute first SQL Command to check if there is duplicate username in database
		String selSql = "select * FROM jad.members where name = ?;";
		PreparedStatement pstmt1 = conn.prepareStatement(selSql);
		pstmt1.setString(1, username);
		ResultSet rs = pstmt1.executeQuery();

		if (rs.next()) { //if there is one record
			//set the found flag
			exist = true;
		} else {
			//present the not found/error message
		}

		//if username, password is not null and if the username do not exist in the database, then they are able to sign up, if not the user is unable to sign up
		if (!exist && (username != "" || password != "")) {
			String insSqlStr = "INSERT INTO jad.members (name,password,role) VALUES (?,?,'member');";
			PreparedStatement pstmt2 = conn.prepareStatement(insSqlStr);
			pstmt2.setString(1, username);
			pstmt2.setString(2, password);
			System.out.print(insSqlStr);
			int count = pstmt2.executeUpdate();
			//Process Result
			if (count > 0) {
	%>
	<div class="container1">

		<div class="container">
			<img src="img/tickSuccess.png" alt="tick" width="170" height="170">
			<h1>SIGN UP SUCCESS!</h1>
			<a href="login.jsp"><button>Go to Login</button></a>
		</div>
	</div>
	<%
	}

	//Close connection
	conn.close();
	} else {
	out.print("sorry signup failed please try again");
	//if the user enter wrg info redirect login.jsp and show a message
	response.sendRedirect("signUpForm.jsp?errCode=invalidLogin");
	}

	} catch (Exception e) {
	out.println("Error :" + e);
	}
	%>
	<br>
	<%@ include file="footer.html"%>
</body>
</html>