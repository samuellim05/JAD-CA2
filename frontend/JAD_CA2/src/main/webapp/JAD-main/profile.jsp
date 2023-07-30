<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/profile.css">
<meta charset="ISO-8859-1">
<title>Profile</title>


</head>
<body>

	<%@ include file="header.html"%>

	<%@ include file="navbar.jsp"%>

	<%

		String user = "";
		int userID = 0;
		String loginStatus = "";
		
		if ((String)session.getAttribute("sessUsername")==null) {
			
	%>
	
			<h2>You are currently not logged in.</h2>
			<a href='index.html'>Sign up or Sign in</a>
	<%
		} else {
			//get login id and password
			user = (String)session.getAttribute("sessUsername");
			userID = (Integer)session.getAttribute("sessUserID");
			loginStatus = (String)session.getAttribute("loginStatus");
		}
		
	
	String id;
	String name;
	String password;
	String role;
	
	try {
		// Step1: Load JDBC Driver
		Class.forName("com.mysql.jdbc.Driver");

		// Step 2: Define Connection URL
		String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=ade00m6m7fjwiydwr4o1&password=pscale_pw_w3m3RVv1nE2cSnC7WO2BaT0XV6I2vqwsth4d5HlZrKF&serverTimezone=UTC";

		// Step 3: Establish connection to URL
		Connection conn = DriverManager.getConnection(connURL);
		// Step 4: Create Statement object
		Statement stmt = conn.createStatement();
		// Step 5: Execute SQL Command
		String sqlStr = "SELECT * FROM jad.members WHERE id='" + userID + "'";
		ResultSet rs = stmt.executeQuery(sqlStr);

		// Step 6: Process Result
		while (rs.next()) {
			id = rs.getString("id");
			name = rs.getString("name");
			password = rs.getString("password");
			role = rs.getString("role");
			
			out.print("<div class='container'>");
			out.print("<div class='box'>");
			
			out.print("<h1>Profile</h1>");
			
			out.print("<form action='editProfile.jsp' method='post'>");	
			
			//uneditable
			out.print("<p>ID:  <input class='inputbox' name='editID' value='" + id + "' readonly></p>"); 		
			
			out.print("<p>Username: <input class='inputbox' type='text' name='editName' value='" + name + "'>");
			
			out.print("<p>Password: <input class='inputbox' type='text' name='editPwd' value='" + password + "'>");
			
			//uneditable
			out.print("<p>Role:  <input class='inputbox' value='" + role + "' readonly></p>"); 		
			
			out.print("<input type='submit' class='btn' value='Edit Profile'>");
			out.print("</form>");
			
			out.print("<form action='logout.jsp' method='post'>"); // Create a form for sign out
			out.print("<input type='submit' class='btn' value='Sign out'>"); // Sign out button
			out.print("</form>");
					
			out.print("<form action='deleteProfile.jsp' method='post'>"); // Create a form for sign out
			out.print("<input type='hidden' name='deleteID' value='" + id + "'>");
			out.print("<input type='submit' class='btn' value='Delete account'>"); // Sign out button
			out.print("</form>");
					
					
			  String message = (String) session.getAttribute("message");
		       if (session.getAttribute("message")!=null) {
		    
		  	  out.print("<p>" + message + "</p>");
		   	  } 

			
			
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