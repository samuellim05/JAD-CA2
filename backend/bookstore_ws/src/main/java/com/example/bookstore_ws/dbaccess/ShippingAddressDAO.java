package com.example.bookstore_ws.dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShippingAddressDAO {
	public ShippingAddress getShippingAddressById(int shippingId) throws SQLException{
		Connection conn = null;
		ShippingAddress shippingAddr = null;
		try {
			conn = DBConnection.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM jad.shipping_address WHERE shipping_id = ?");
			pstmt.setInt(1, shippingId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				shippingAddr = new ShippingAddress();
				shippingAddr.setShipping_id(rs.getInt("shipping_id"));
				shippingAddr.setUser_id(rs.getInt("user_id"));
				shippingAddr.setAddress(rs.getString("address"));
				shippingAddr.setAddress2(rs.getString("address2"));
				shippingAddr.setDistrict(rs.getString("district"));
				shippingAddr.setPostal_code(rs.getString("postal_code"));
				shippingAddr.setCity(rs.getString("city"));
				shippingAddr.setCountry(rs.getString("country"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shippingAddr;
	}
	
	public int createAddr (ShippingAddress shippingAddr) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "INSERT INTO shipping_address (user_id, address, address2, district, postal_code, city, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            
            pstmt.setInt(1, shippingAddr.getUser_id());
            pstmt.setString(2, shippingAddr.getAddress());
            pstmt.setString(3, shippingAddr.getAddress2());
            pstmt.setString(4, shippingAddr.getDistrict());
            pstmt.setString(5, shippingAddr.getPostal_code());
            pstmt.setString(6, shippingAddr.getCity());
            pstmt.setString(7, shippingAddr.getCountry());
            
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

