package com.example.bookstore_ws.dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import assignment.Cart;
import assignment.User;

public class CartDAO {
	public ArrayList<Cart> getCartByMemberId(int memberId) {
		Connection conn = null;
		ArrayList<Cart> cartList = new ArrayList<>();
		try {
	          
	          conn = DBConnection.getConnection(); 
	        //sql command
				String selStr = "SELECT b.title, b.price, c.quantityInCart FROM cart c, books b where c.book_id=b.id AND c.member_id = ?;";
				//prepare statement
				PreparedStatement pstmt = conn.prepareStatement(selStr);
				//set the values
				pstmt.setInt(1, memberId);
				// Step 5: Execute SQL Command

				ResultSet rs = pstmt.executeQuery();

				// Step 6: Process Result
				while (rs.next()) {
					Cart cart = new Cart();
					cart.setTitle(rs.getString("title"));
					cart.setPrice(rs.getDouble("price"));
					cart.setQuantityInCart(rs.getInt("quantityInCart"));
					cartList.add(cart);
				}
	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
		return cartList;
	}
}
