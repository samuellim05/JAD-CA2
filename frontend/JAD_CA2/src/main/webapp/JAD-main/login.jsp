<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/signIn.css">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>


<body>

	<%
	String message = request.getParameter("errCode");

	//if error, print msg
	if (message != null && message.equals("invalidLogin")) {
		out.print("Sorry, error in login... <h2>Please try again!</h2>");
	}
	
	if (message != null && message.equals("accountDeleted")) {
		out.print("<h2>Account deleted successfully!</h2>");
	}
	%>
	
	

	<div class="container1">
	<div class="fLeft">
			THE<br>SAMAS<br>SHOP
		</div>
		<div class="container">
			<div class="bg-img"></div>
			<!-- Input values here -->
			<form action="<%=request.getContextPath() %>/VerifyUserCA1" method="post">
			<h1>SIGN IN</h1>
			<div class="input-data">
					<label>Username:</label><br> <input type="text"
						name="loginid" required><br>
				</div>
				<div class="input-data">
					<label>Password:</label><br> <input type="password"
						name="password" required><br>
				</div>
				<div class="input-data">
					<button type="submit" class="submitBtn"  name="btnSubmit">Sign In</button>
				</div>
				
			</form>

			<p>--------------------OR--------------------</p>
			<button class="btn" onclick="location.href='homepage.jsp'">Continue as Guest</button>

			<p>
				Don't have an account?<a href='signUpForm.jsp'>Sign up</a>
			</p>

		</div>
	</div>
	<div class="footer">
	<%@ include file="footer.html"%>
	</div>
</body>
</html>