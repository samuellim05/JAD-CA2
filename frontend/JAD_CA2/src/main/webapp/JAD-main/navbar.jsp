<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
/* CSS for navbar */
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Comfortaa&display=swap');
a {
	font-family: 'Comfortaa', cursive;
	color: #FFFFF0;
	font-size: 20px;
}

.dropbtn {
	background-color: #1E7976;
	font-family: 'Kosugi Maru', sans-serif;
	color: #FFFFF0;
	font-size: 20px;
	padding: 16px 10px;
	border: none;
	font-weight: normal;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #6495ED;
	min-width: 160px;
	z-index: 1;
}

.navbar {
	display: flex;
	background-color: #1E7976;
	height:80px;
}

.logo {
	display: flex;
	align-items: center;
	justify-content: flex-start;
	font-family: 'Black Han Sans', sans-serif;
	color: #FFFFF0;
	margin-right: auto;
	padding-left:30px;
	font-size: 30px;
}

.navbar-links {
	display: flex;
	justify-content: flex-end;
	align-items: center;
}

.links, .dropdown-links {
	display: inline-block;
	color: white;
	padding: 16px;
	text-decoration: none;
	font-size: 20px;
}

.dropdown-links {
	display: block;
}

.links {
	display: inline-block;
}

.dropdown-links:hover, .links:hover {
	background-color: lightblue;
}

/*display dropdown*/
.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: lightblue;
}

.dropdown-arrow {
	vertical-align: middle;
}
</style>

</head>
<body>

	<nav>
		<div class="navbar">
			<p class="logo">SAMAS</p>
			<div class="navbar-links">
			<a class="links" href="homepage.jsp">Home</a>
			<div class="dropdown">
				<button class="dropbtn">Category<span class="dropdown-arrow">&#9662;</span></button>
				<div class="dropdown-content">
					<a class="dropdown-links" href="category.jsp?category=Horror">Horror</a> 
					<a class="dropdown-links" href="category.jsp?category=Sci-Fi">Sci-Fi</a> 
					<a class="dropdown-links" href="category.jsp?category=Romance">Romance</a> 
					<a class="dropdown-links" href="category.jsp?category=Manga">Manga</a>
					<a class="dropdown-links" href="category.jsp?category=Fantasy">Fantasy</a> 
					<a class="dropdown-links" href="category.jsp?category=Mystery">Mystery</a> 
				</div>
			</div>
			<a class="links" href="browse.jsp">Browse</a>
			<a class="links" href="displayCart.jsp">Cart</a>
			<a class="links" href="about.jsp">About</a>
			<a class="links" href="contactUs.jsp">Contact Us</a>
			<a class="links" href="profile.jsp">Profile</a>
		</div>
		</div>
	</nav>
</body>
</html>