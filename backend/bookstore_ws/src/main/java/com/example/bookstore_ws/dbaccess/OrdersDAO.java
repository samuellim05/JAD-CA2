package com.example.bookstore_ws.dbaccess;
import com.example.bookstore_ws.dbaccess.Orders;
import java.sql.*;
import java.util.ArrayList;

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
	
	// get all orders
    public ArrayList<Orders> listAllOrders() throws SQLException {
        ArrayList<Orders> orderList = new ArrayList<Orders>();
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            String sqlStr = "SELECT * FROM orders";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setOrder_id(rs.getInt("order_id"));
                orders.setUser_id(rs.getInt("user_id"));
                orders.setOrder_date(rs.getString("order_date"));
                orders.setTotal_cost(rs.getDouble("total_cost"));
                orders.setShipping_id(rs.getInt("shipping_id"));
                orders.setStatus(rs.getString("status"));
               
                orderList.add(orders);
            }
            System.out.println("....done fetching all orders!......");
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return orderList;
    }

    
    // for user's history
    public ArrayList<Orders> getOrdersByUserId(int uid) throws SQLException {
        Connection conn = null;
        ArrayList<Orders> ordersList = new ArrayList<Orders>();
        try {
            conn = DBConnection.getConnection();
            String sqlStr = "SELECT * FROM orders WHERE user_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            pstmt.setInt(1, uid);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Orders orders = new Orders();
                orders.setOrder_id(rs.getInt("order_id"));
                orders.setUser_id(rs.getInt("user_id"));
                orders.setOrder_date(rs.getString("order_date"));
                orders.setTotal_cost(rs.getDouble("total_cost"));
                orders.setShipping_id(rs.getInt("shipping_id"));
                orders.setStatus(rs.getString("status"));
                ordersList.add(orders);
            }

        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            conn.close();
        }
        return ordersList;
    }
    
    public int updateStatus(int oid, Orders orders) {
    	Connection conn = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			orders = new Orders();

			//sql command
	          String updateStr = "UPDATE jad.orders set status=? WHERE order_id=?;";
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(updateStr);
	          //set the values
	          pstmt.setString(1, orders.getStatus());
				
	          count = pstmt.executeUpdate();
	          // Step 6: Process Result
	          if (count > 0) 
	        	  System.out.println (count + " records inserted");

	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
		return count;
    }
}
