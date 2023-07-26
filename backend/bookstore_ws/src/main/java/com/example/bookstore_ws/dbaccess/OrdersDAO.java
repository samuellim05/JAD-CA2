package com.example.bookstore_ws.dbaccess;
import com.example.bookstore_ws.dbaccess.Orders;
import java.sql.*;

public class OrdersDAO {
	public int createOrder(Orders orders) throws SQLException {
		Connection conn = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			//sql command
	          String insertStr = "INSERT INTO orders (user_id, total_cost, shipping_id) VALUES (?,?,?);"; 
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(insertStr);
	          //set the values
	          pstmt.setInt(1, orders.getUser_id());
				pstmt.setDouble(2, orders.getTotal_cost());
				pstmt.setInt(3, orders.getShipping_id());

	          count = pstmt.executeUpdate();
	          // Step 6: Process Result
	          if (count > 0) System.out.println (count + " records inserted");

	          // Step 7: Close connection
	          conn.close();
		} catch (SQLException e) {
			// Print out any exceptions that might be thrown
			e.printStackTrace();
		}

		return count;
	}
}
