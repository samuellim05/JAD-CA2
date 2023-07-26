package com.example.bookstore_ws.dbaccess;

public class CartDAO {
	public Cart getCartById(int cartId) throws SQLException{
		Connection conn = null;
		User user = null;
		try {
			conn = DBConnection.getConnection(); 
	          //sql command
	          String selStr = "SELECT * FROM jad.books WHERE id = ?"; 
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(selStr);
	          //set the values
	          pstmt.setInt(1, iBookID);
	       // Step 5: Execute SQL Command

	  		ResultSet rs = pstmt.executeQuery();

	  		// Step 6: Process Result
	  		while (rs.next()) {
	  	        title = rs.getString("title");
	  	        author= rs.getString("author");
	  	        price = rs.getDouble("price");
	  	        quantity = rs.getInt("quantity");
	  	        publisher = rs.getString("publisher");
	  	        publication_date = rs.getString("publication_date");
	  	        isbn = rs.getString("ISBN");
	  	        genre = rs.getString("genre");
	  	        rating = rs.getString("rating");
	  	        description = rs.getString("description");
	  	        bookCover = rs.getString("img");
	  		}
	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
		}
	}
}
