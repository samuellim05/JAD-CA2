<!--
	-Name: Samuel Lim Zhi En
	-Class: DIT/2A/02
	-Admission no. 2227874
  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="assignment.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/table.css">
<meta charset="ISO-8859-1">
<title>Display Cart</title>
</head>


<%@ include file="navbar.jsp"%>
<body>
	<%
	boolean check = false;
	double total = 0.0;
	%>
	<h1>CART</h1>
	<div class="container2">
		<%--create a table to store the book data --%>
		<table border="1">
			<tr>
				<td>Book Cover</td>
				<td>Title</td>
				<td>Author</td>
				<td>Publisher</td>
				<td>Price</td>
				<td>Quantity</td>
				<td>Action</td>
			</tr>
			<%
			String role = (String) session.getAttribute("sessUserRole");
			
			//if user is a member, the user's cart details will be stored in the database
			if (role != null && role.equals("member")) {
				int memberId = (int) session.getAttribute("sessUserID");
				int bookID = 0;
				String img = "";
				String title = "";
				String author = "";
				String publisher = "";
				double price = 0.0;
				int quantity = 0;
				try {
					// Step1: Load JDBC Driver
					Class.forName("com.mysql.jdbc.Driver");

					// Step 2: Define Connection URL
					String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

					// Step 3: Establish connection to URL
					Connection conn = DriverManager.getConnection(connURL);
					//sql command
					String selStr = "SELECT books.id, books.img, books.title, books.author, books.publisher, books.price, cart.quantity FROM jad.books, jad.cart where cart.book_id = books.id AND cart.member_id=?;";
					//prepare statement
					PreparedStatement pstmt = conn.prepareStatement(selStr);
					//set the values
					pstmt.setInt(1, memberId);
					// Step 5: Execute SQL Command

					ResultSet rs = pstmt.executeQuery();

					// Step 6: Process Result
					while (rs.next()) {
				bookID = rs.getInt("id");
				img = rs.getString("img");
				title = rs.getString("title");
				author = rs.getString("author");
				publisher = rs.getString("publisher");
				price = rs.getDouble("price");
				quantity = rs.getInt("quantity");
				
				//get the total cost for all the duplicate books in the cart
				total += (price * quantity);
			%>
			<tr>
				<td><img src="<%=img%>" alt="book Cover" width="50" height="50"></td>
				<td><%=title%></td>
				<td><%=author%></td>
				<td><%=publisher%></td>
				<td>$<%=price%></td>
				<!-- actions the users can perform to edit the quantity of the books in their cart -->
				<td><a
					href="editCart.jsp?action=minus&bookID=<%=bookID%>&quantity=<%=quantity%>"><button>-</button></a><%=quantity%><a
					href="editCart.jsp?action=add&bookID=<%=bookID%>&quantity=<%=quantity%>"><button>+</button></a></td>
				<td><a
					href="editCart.jsp?action=delete&bookID=<%=bookID%>&quantity=<%=quantity%>"><button>delete</button></a></td>
			</tr>
			
			<%
			}
			%>
			<tr>
				<td colspan="3">Total</td>
				<td colspan="3">$<%=total%></td>
				<td><a
					href="editCart.jsp?action=clear&quantity="><button>Clear All</button></a></td>
			</tr>
			<%

			// Step 7: Close connection
			conn.close();
			} catch (Exception e) {
			System.out.println("Error :" + e);
			}

			} else {
			//if the user is a guest, the users cart details will be stored in cookies
			//get the arraylist data from cookies session
			ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("bookCart");
			if (cart != null) {
			check = true;
			for (int i = 0; i < cart.size(); i++) {
			Cart book = cart.get(i);
			total += book.getTotal();
			%>
			<tr>
				<td><img src="<%=book.getImg()%>" alt="book Cover" width="50"
					height="50"></td>
				<td><%=book.getTitle()%></td>
				<td><%=book.getAuthor()%></td>
				<td><%=book.getPublisher()%></td>
				<td>$<%=book.getPrice()%></td>
				<!-- edit cart details -->
				<td><a
					href="editCart.jsp?action=minus&title=<%=book.getTitle()%>"><button>-</button></a><label><%=book.getQuantityInCart()%></label><a
					href="editCart.jsp?action=add&title=<%=book.getTitle()%>"><button>+</button></a></td>
				<td><a
					href="editCart.jsp?action=delete&title=<%=book.getTitle()%>"><button>delete</button></a></td>
			</tr>

			<%
			}
			%>

			<tr>
				<td colspan="3">Total</td>
				<td colspan="3">$<%=total%></td>
				<td><a
					href="editCart.jsp?action=clear"><button>Clear All</button></a></td>
			</tr>
			
			<%
			} else {
			response.sendRedirect("browse.jsp");
			return;
			}
			}
			%>

		</table>
		<a href="browse.jsp"><button>Go back</button></a>
	</div>

</body>

<%@ include file="footer.html"%>
</html>