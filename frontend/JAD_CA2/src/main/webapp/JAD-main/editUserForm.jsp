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
<link rel="stylesheet" type="text/css" href="css/table.css">
<meta charset="ISO-8859-1">
<title>Edit Member</title>
</head>
<body>
<%@ include file="header.html"%>
	<%@ include file="adminNav.html"%>
	<%
	//session management to prevent public from accessing
	String user = request.getParameter("name");
	String userRole = (String) session.getAttribute("sessUserRole");
	if (userRole == null||userRole.equals("member")||!userRole.equals("admin")) {
		response.sendRedirect("denyAccess.jsp");
	}
	%>
	<%
	String sID = request.getParameter("id");
	int iID = Integer.parseInt(sID);
	String name = "";
	String role = "";

	//sql connection
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
		String sqlStr = "SELECT * FROM jad.members WHERE id=?;";
		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		pstmt.setInt(1, iID);
		System.out.print(sqlStr);
		ResultSet rs = pstmt.executeQuery();

		// Step 6: Process Result
		while (rs.next()) {
			iID = rs.getInt("id");
			name = rs.getString("name");
			role = rs.getString("role");
		}
	%>
<div class="container2">
<form action="<%=request.getContextPath() %>/updateUser" method="post" class="addBookForm">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Role</th>
			</tr>
			<tr>
				<td><input name="eID" value=<%=iID%> readonly></td>
				<td><input name="eName" value=<%=name%>></td>
				<!-- select user role -->
				<td><select name="eRole">
						<option value="admin" <% if (role.equals("admin")) { %>selected<% } %>>Admin</option>
						<option value="member"  <% if (role.equals("member")) { %>selected<% } %>>Member</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: center;"><input
					type='submit' name='btnSubmit' value='Submit'></td>
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