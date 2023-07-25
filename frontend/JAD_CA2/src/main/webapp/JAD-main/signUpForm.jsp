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
<link rel="stylesheet" href="css/signIn.css">
<meta charset="ISO-8859-1">
<title>Sign Up</title>
</head>
<%@ include file="header.html"%>
<body>
	<div class="container1">
		<div class="fLeft">
			THE<br>SAMAS<br>SHOP
		</div>
		<div class="container">
			<div class="bg-img"></div>
			
			<!-- sign up form -->
			<form action="signUp.jsp" method="post">
				<h1>SIGN UP</h1>
				<div class="input-data">
					<label>Username:</label><br> <input type="text"
						name="username" required><br>
				</div>
				<div class="input-data">
					<label>Password:</label><br> <input type="password"
						name="password" required><br>
				</div>

				<div class="input-data">
					<button type="submit" class="submitBtn">Sign Up</button>
				</div>



			</form>
			<a href="login.jsp"><button>Go To Sign In</button></a><br>
			<%-- invalid signup displays an invalid signup text --%>
			<%
			String err = request.getParameter("errCode");
			if (err != null && err.equals("invalidLogin")) {
				out.println("Username is taken :(");
			}
			%>
		</div>
	</div>
	<div class="footer">
	<%@ include file="footer.html"%>
	</div>
</body>



</html>