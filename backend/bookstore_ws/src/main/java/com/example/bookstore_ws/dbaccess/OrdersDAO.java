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
    
    public int updateStatus(int oid, String status) {
    	Connection conn = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();

			//sql command
	          String updateStr = "UPDATE jad.orders set status=? WHERE order_id=?;";
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(updateStr);
	          //set the values
	          pstmt.setString(1, status);
	          pstmt.setInt(2, oid);
				System.out.println(oid);
	          count = pstmt.executeUpdate();
	          // Step 6: Process Result
	          if (count > 0) 
	        	  System.out.println (count + " records inserted;Order id: " + oid + " Status: " + status);

	          // Step 7: Close connection
	          conn.close();
	     } catch (Exception e) {
	        System.out.println("Error :" + e);
	     }
		return count;
    }
    
    public int updateOrder(Orders order, int oid) {
	    Connection conn = null;
	    int count = 0;

	    try {
	        conn = DBConnection.getConnection();
	        String updateSql="UPDATE orders SET user_id = ?, order_date = ?, status = ?, total_cost = ?, shipping_id =? WHERE order_id = ?"; 
	        PreparedStatement pstmt = conn.prepareStatement(updateSql);

	        pstmt.setInt(1, order.getUser_id());
	        pstmt.setString(2, order.getOrder_date());
	        pstmt.setString(3, order.getStatus());
	        pstmt.setDouble(4, order.getTotal_cost());
	        pstmt.setInt(5, order.getShipping_id());
	        pstmt.setInt(6, oid);
	        count = pstmt.executeUpdate();

	        //proccess results
	        if (count>0) {
	            System.out.println("Order with ID " + oid + " updated successfully.");
	        } else {
	            System.out.println("Failed to update order with ID " + oid);
	        }
	        conn.close();
	    } catch (SQLException e) {
	        System.err.println("Error updating order with ID " + order.getOrder_id());
	        e.printStackTrace();
	    } 

	    return count;
	}
    
    public boolean cancelOrder(int oid) {
		Connection conn = null;
		boolean canceled = false;

		try {
			conn = DBConnection.getConnection();
			String cancelSql = "UPDATE orders SET status = 'Cancelled' WHERE order_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(cancelSql);
			pstmt.setInt(1, oid);

			int rowsAffected = pstmt.executeUpdate();
			canceled = (rowsAffected > 0);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return canceled;
	}
}
