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
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/table.css">

<title>View Members</title>
</head>
<body>
	<%@ include file="header.html"%>
	<%@ include file="adminNav.html"%>
	<%
	String user = request.getParameter("name");
	String userRole = (String) session.getAttribute("sessUserRole");
	if (userRole == null||userRole.equals("member")||!userRole.equals("admin")) {
		response.sendRedirect("denyAccess.jsp");
	}
	%>
	<br>
	<div class="container1">
	
	<form action="" method="get" class="search">
		<label>Search User:</label><input type="text" placeholder="Enter User" name="search" class="search-box">
		<label>Filter user by role:</label><select name="role" class ="search-box">
								<option value="All">All</option>
								<option value="admin">Admin</option>
								<option value="member">Member</option>		
							</select>
							<input type='submit' name='btnSubmit' value='Search' class="search-box">
	</form>
	</div>
	<h1>Member list</h1>
	
	<%
	int id = 0;
	String name = "";
	String role = request.getParameter("role");
	if (role == null ||role.equals("All")) {
	    role = ""; 
	   
	}
	String search = request.getParameter("search");
	if (search == null || search.isEmpty()) {
	    search = ""; 
	}
	
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
		String sqlStr = "SELECT * FROM jad.members WHERE name LIKE ? AND role LIKE ?;";
		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		pstmt.setString(1, "%" + search + "%");
		pstmt.setString(2, "%" + role + "%");
		ResultSet rs = pstmt.executeQuery();
	%>
	<div class="container2">
	<table border='1'>
		<tr class="header">
			<th class="table-header">ID</th>
			<th class="table-header">Member</th>
			<th class="table-header">Role</th>
			<th class="table-header">Action</th>
		</tr>

		<%
		// Step 6: Process Result
		while (rs.next()) {
			id = rs.getInt("id");
			name = rs.getString("name");
			role = rs.getString("role");
		%>

		<tr>
			<td><%=id%></td>
			<td><%=name%></td>
			<td><%=role%></td>
			<td><a href='editUserForm.jsp?id=<%=id%>'><button>edit user</button></a>
		</tr>
		<%
		}

		// Step 7: Close connection
		conn.close();
		} catch (Exception e) {
		out.println("Error :" + e);
		}
		%>


	</table>
	</div>
	
</body>
</html>