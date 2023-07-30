
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
	boolean exist = false;

	//----------------START DATABASE CONNECTION----------------------

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

		if (!exist) {

			String sqlStr = "UPDATE jad.members SET members.name=?, members.password=? WHERE members.id=?";

			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, username);
			pstmt.setString(2, pwd);
			pstmt.setInt(3, id);

			int affectedRows = pstmt.executeUpdate();

			// Step 6: Process Result
			if (affectedRows > 0) {
		//set the found flag
		found = true;

		//request.setAttribute("message", "Database updated successfully");

		/*
		String sqlStr = "UPDATE jad.members SET name='u1', password='p1' WHERE id=1";
		
		int affectedRows = stmt.executeUpdate(sqlStr);
		*/

		session.setAttribute("message", "Profile updated successfully");
		System.out.println("Number of affected rows: " + affectedRows);

			} else {
		//print out not foiund message
		
			}
		} else if (exist) {
			//print out user name is already taken
			session.setAttribute("message", "Username is already taken");
		}

		// Step 7: Close connection
		conn.close();
	} catch (Exception e) {
		out.println("Error :" + e);
	}

	response.sendRedirect("profile.jsp"); // Brings user to homepage
	%>


</body>
</html>