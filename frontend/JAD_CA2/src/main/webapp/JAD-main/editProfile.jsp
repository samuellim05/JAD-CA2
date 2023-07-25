
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>edit profile</title>
</head>
<body>


	<%
	//get login id and password
	String strID = request.getParameter("editID");
	int id = Integer.parseInt(strID);
	String username = request.getParameter("editName");
	String pwd = request.getParameter("editPwd");
	
	boolean found = false;

	//----------------START DATABASE CONNECTION----------------------

	try {
		// Step1: Load JDBC Driver
		Class.forName("com.mysql.jdbc.Driver");

		// Step 2: Define Connection URL
		String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

		// Step 3: Establish connection to URL
		Connection conn = DriverManager.getConnection(connURL);
		// Step 4: Create Statement object
		Statement stmt = conn.createStatement();
		// Step 5: Execute SQL Comman
		String sqlStr = "UPDATE jad.members SET members.name=?, members.password=? WHERE members.id=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		pstmt.setString(1,username);
		pstmt.setString(2,pwd);
		pstmt.setInt(3,id);
		
		int affectedRows = pstmt.executeUpdate();
		
		// Step 6: Process Result
		if (affectedRows>0) {
			//set the found flag
			found = true;

			// update profile msg
			session.setAttribute("message", "Profile updated successfully");
			
            System.out.println("Number of affected rows: " + affectedRows); // for debugging
       
		} else {
			//print out not foiund message
		}

		// Step 7: Close connection
		conn.close();
	} catch (Exception e) {
		out.println("Error :" + e);
	}

	response.sendRedirect("profile.jsp"); // Brings user back to profile 
	
	%>


</body>
</html>