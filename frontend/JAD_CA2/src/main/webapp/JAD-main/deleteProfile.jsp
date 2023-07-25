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
	String strID = request.getParameter("deleteID");
	int id = Integer.parseInt(strID);
	

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
		// Step 5: Execute SQL Command
		
		String sqlStr = "DELETE FROM jad.members WHERE members.id=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		pstmt.setInt(1,id);
		
		int affectedRows = pstmt.executeUpdate();
				
		// Step 6: Close connection
		conn.close();
	} catch (Exception e) {
		out.println("Error :" + e);
	}
	
	session.invalidate(); //clear session
	response.sendRedirect("login.jsp?errCode=accountDeleted"); // Brings user back to login
	
	%>


</body>
</html>