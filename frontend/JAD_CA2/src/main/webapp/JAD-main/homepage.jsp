<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>

<link rel="stylesheet" href="css/homepage.css">

</head>

<body>



	<%@ include file="navbar.jsp"%>

	<div class="container">
		<div class="selCat">
			SELECT<br>CATEGORY:
		</div>
		<table class="btnTable">
			<tr>
				<td><a href="category.jsp?category=Horror"><button class="catBtn">HORROR</button></a></td>
				<td><a href="category.jsp?category=Sci-Fi"><button class="catBtn">SCI-FI</button></a></td>
				<td><a href="category.jsp?category="><button class="catBtn">SELF HELP</button></a></td>
			</tr>
			<tr>
				<td><a href="category.jsp?category=5"><button class="catBtn">FANTASY</button></a></td>
				<td><a href="category.jsp?category=3"><button class="catBtn">ROMANCE</button></a></td>
				<td><a href="category.jsp?category="><button class="catBtn">COOKBOOK</button></a></td>
			</tr>
			<tr>
				<td><a href="category.jsp?category="><button class="catBtn">CHILDREN</button></a></td>
				<td><a href="category.jsp?category=Manga"><button class="catBtn">MANGA</button></a></td>
				<td><a href="category.jsp?category=6"><button class="catBtn">MYSTERY</button></a></td>
			</tr>
		</table>
		
		
<!-- 		<div class="row"> -->
<!-- 			<div class="box"> -->
<!-- 				<a class="header" href="category.jsp?category=Horror">Horror</a> -->
<!-- 				<p>Delve into the realms of fear and the supernatural, evoking -->
<!-- 					spine-chilling suspense and terror.</p> -->
<!-- 				<button onclick="location.href='category.jsp?category=Horror'">See More</button> -->
<!-- 			</div> -->
<!-- 			<div class="box"> -->
<!-- 				<a class="header" href="category.jsp?category=Sci-Fi">Sci-Fi</a> -->
<!-- 				<p>Explore imaginative futuristic concepts, often incorporating -->
<!-- 					advanced technology, space exploration, and speculative scientific -->
<!-- 					ideas.</p> -->
<!-- 				<button onclick="location.href='category.jsp?category=Sci-Fi'">See More</button> -->
<!-- 			</div> -->
<!-- 			<div class="box"> -->
<!-- 				<a class="header" href="category.jsp?category=3">Romance</a> -->
<!-- 				<p>Experience the power of love and the complexities of human -->
<!-- 					connections as hearts intertwine, passions ignite, and personal -->
<!-- 					growth and emotional bonds are explored in heartwarming and -->
<!-- 					sometimes tumultuous relationships.</p> -->
<!-- 				<button onclick="location.href='category.jsp?category=Romance'">See More</button> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="box"> -->
<!-- 				<a class="header" href="category.jsp?category=Manga">Manga</a> -->
<!-- 				<p>Graphic novels which originated from Japan that features -->
<!-- 					expressive artwork and captivating narratives, often drawing -->
<!-- 					inspiration from various aspects of Japanese culture.</p> -->
<!-- 				<button onclick="location.href='category.jsp?category=Manga'">See More</button> -->
<!-- 			</div> -->
<!-- 			<div class="box"> -->
<!-- 				<a class="header" href="category.jsp?category=5">Fantasy</a> -->
<!-- 				<p>Immerse yourself in magical realms and mythical creatures -->
<!-- 					where extraordinary quests, epic battles, and the clash of light -->
<!-- 					and darkness shape the destiny of heroes and the fate of -->
<!-- 					fantastical worlds.</p> -->
<!-- 					<button onclick="location.href='category.jsp?category=Fantasy'">See More</button> -->
<!-- 			</div> -->
<!-- 			<div class="box"> -->
<!-- 				<a class="header" href="category.jsp?category=6">Mystery</a> -->
<!-- 				<p>Delve into the realms of suspense and intrigue as complex -->
<!-- 					puzzles, elusive clues, and enigmatic characters intertwine, -->
<!-- 					leading to the thrilling revelation of the truth.</p> -->
<!-- 				<button onclick="location.href='category.jsp?category=Mystery'">See More</button> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</div>


	<%@ include file="footer.html"%>
</body>

</html>