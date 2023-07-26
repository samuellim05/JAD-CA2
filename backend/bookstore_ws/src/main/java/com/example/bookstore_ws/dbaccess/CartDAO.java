package com.example.bookstore_ws.dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.bookstore_ws.dbaccess.Cart;
import com.example.bookstore_ws.dbaccess.User;

public class CartDAO {
	public ArrayList<Cart> getCartByMemberId(int memberId) {
		Connection conn = null;
		ArrayList<Cart> cartList = new ArrayList<>();
		try {

			conn = DBConnection.getConnection();
			// sql command
			String selStr = "SELECT b.title, b.price, c.quantityInCart FROM cart c, books b where c.book_id=b.id AND c.member_id = ?;";
			// prepare statement
			PreparedStatement pstmt = conn.prepareStatement(selStr);
			// set the values
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

	public int updateCart(int memberId, int bookId, String action) {
	    Connection conn = null;
	    Cart cart = new Cart();
	    int count = 0;
	    System.out.println(action);
	    try {
	        conn = DBConnection.getConnection();

	        // Retrieve the current quantity in the cart
	        String query = "SELECT quantityInCart FROM jad.cart WHERE member_id = ? and book_id = ?";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, memberId);
	        pstmt.setInt(2, bookId);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            int currentQuantity = rs.getInt("quantityInCart");

	            // Perform action based on input
	            switch (action) {
	                case "add":
	                    currentQuantity += 1;
	                    break;
	                case "minus":
	                    if (currentQuantity > 1) {
	                        currentQuantity -= 1;
	                    } else {
	                        // If quantity is 1 or less, remove the item from the cart
	                        String delStr = "DELETE FROM jad.cart WHERE member_id = ? AND book_id = ?";
	                        PreparedStatement deleteStmt = conn.prepareStatement(delStr);
	                        deleteStmt.setInt(1, memberId);
	                        deleteStmt.setInt(2, bookId);
	                        count = deleteStmt.executeUpdate();
	                        // No need to proceed further, return the result count
	                        conn.close();
	                        return count;
	                    }
	                    break;
	                case "delete":
	                    // Delete the item from the cart
	                    String delStr = "DELETE FROM jad.cart WHERE member_id = ? AND book_id = ?";
	                    PreparedStatement deleteStmt = conn.prepareStatement(delStr);
	                    deleteStmt.setInt(1, memberId);
	                    deleteStmt.setInt(2, bookId);
	                    count = deleteStmt.executeUpdate();
	                    // No need to proceed further, return the result count
	                    conn.close();
	                    return count;
	                case "clear":
	                    // Clear all items from the cart
	                    String clearStr = "DELETE FROM jad.cart WHERE member_id = ?";
	                    PreparedStatement clearStmt = conn.prepareStatement(clearStr);
	                    clearStmt.setInt(1, memberId);
	                    count = clearStmt.executeUpdate();
	                    // No need to proceed further, return the result count
	                    conn.close();
	                    return count;
	                default:
	                    System.out.println("Invalid action: " + action);
	                    conn.close();
	                    return 0;
	            }

	            // Update the quantity in the cart
	            String updateStr = "UPDATE jad.cart SET quantityInCart = ? WHERE member_id = ? and book_id = ?";
	            PreparedStatement updateStmt = conn.prepareStatement(updateStr);
	            updateStmt.setInt(1, currentQuantity);
	            updateStmt.setInt(2, memberId);
	            updateStmt.setInt(3, bookId);
	            count = updateStmt.executeUpdate();
	        }
	        conn.close();
	    } catch (Exception e) {
	        System.out.println("Error: " + e);
	    }
	    return count;
	}

}
