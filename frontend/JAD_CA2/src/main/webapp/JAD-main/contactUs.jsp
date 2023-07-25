<!--
	-Name: Samuel Lim Zhi En
	-Class: DIT/2A/02
	-Admission no. 2227874
  -->

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/contactUs.css">
<meta charset="ISO-8859-1">
<title>Contact Us!</title>
</head>
<%@ include file="header.html"%>


<%@ include file="navbar.jsp"%>
<body>
	<div class="container1">
		<h1>Contact us Form</h1>
		<div class="container">
			<div class="bg-img"></div>
			<!-- Contact Us Form -->
			<form action="successContact.jsp" >
				<div class="input-data">
					<label for="">First Name</label><br> <input type="text"
						required>
					<div class="underline"></div>
				</div>
				<div class="input-data">
					<label for="">Last Name</label><br> <input type="text"
						required>
					<div class="underline"></div>
				</div>
				<div class="input-data">
					<label for="">Email Address</label><br> <input type="email"
						required>
					<div class="underline"></div>
				</div>
				<div class="form-row">
					<div class="input-data textarea">
						<label for="">Write your message</label><br>
						<textarea rows="8" cols="42" required></textarea>
						<br />
						<div class="underline"></div>
						<br />
						<button type="submit" class="submitBtn">Send</button>
					</div>
				</div>

			</form>
		</div>
		
	</div>


</body>

<%@ include file="footer.html"%>
</html>