package com.example.bookstore_ws.dbaccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.example.bookstore_ws.dbaccess.User;

public class UserDAO {
	public User getMemberById(String memberId) throws SQLException{
		Connection conn = null;
		User user = null;
		try {
			conn = DBConnection.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM jad.members where id = ?");
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setPassword(rs.getString("password"));
	            user.setRole(rs.getString("role"));
	            user.setEmail(rs.getString("email"));
	            user.setNumber(rs.getString("number"));
	            user.setAddress(rs.getString("address"));
	            user.setAddress2(rs.getString("address2"));
	            user.setDistrict(rs.getString("district"));
	            user.setCity(rs.getString("city"));
	            user.setPostalCode(rs.getString("postal_code"));
	            user.setCountry(rs.getString("country"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return user;
	}
	
	public ArrayList<User> getAllUsers() throws SQLException{
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();

			Statement stmt = conn.createStatement();
			String sqlStr = "SELECT * FROM jad.members";
			ResultSet rs = stmt.executeQuery(sqlStr);
			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setPassword(rs.getString("password"));
	            user.setRole(rs.getString("role"));
	            user.setEmail(rs.getString("email"));
	            user.setNumber(rs.getString("number"));
	            user.setAddress(rs.getString("address"));
	            user.setAddress2(rs.getString("address2"));
	            user.setDistrict(rs.getString("district"));
	            user.setCity(rs.getString("city"));
	            user.setPostalCode(rs.getString("postal_code"));
	            user.setCountry(rs.getString("country"));
				userList.add(user);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public int createUser(User user) {
		Connection conn = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();

			//sql command
	          String insertStr = "INSERT INTO jad.members (name, password, role, email, number, address, address2, district, city, postal_code, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	          //prepare statement
	          PreparedStatement pstmt = conn.prepareStatement(insertStr);
	          //set the values
	          pstmt.setString(1, user.getName());
	          pstmt.setString(2, user.getPassword());
	          pstmt.setString(3, user.getRole());
	          pstmt.setString(4, user.getEmail());
	          pstmt.setString(5, user.getNumber());
	          pstmt.setString(6, user.getAddress());
	          pstmt.setString(7, user.getAddress2());
	          pstmt.setString(8, user.getDistrict());
	          pstmt.setString(9, user.getCity());
	          pstmt.setString(10, user.getPostalCode());
	          pstmt.setString(11, user.getCountry());

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
	
	public int updateUser(User user, String id) {
	    Connection conn = null;
	    int count = 0;

	    try {
	        conn = DBConnection.getConnection();
	        // SQL command
	        String updateStr = "UPDATE jad.members SET name=?, password=?, role=?, email=?, number=?, address=?, address2=?, district=?, city=?, postal_code=?, country=? WHERE id=?";
	        // Prepare statement
	        PreparedStatement pstmt = conn.prepareStatement(updateStr);
	        // Set the values
	        pstmt.setString(1, user.getName());
	        pstmt.setString(2, user.getPassword());
	        pstmt.setString(3, user.getRole());
	        pstmt.setString(4, user.getEmail());
	        pstmt.setString(5, user.getNumber());
	        pstmt.setString(6, user.getAddress());
	        pstmt.setString(7, user.getAddress2());
	        pstmt.setString(8, user.getDistrict());
	        pstmt.setString(9, user.getCity());
	        pstmt.setString(10, user.getPostalCode());
	        System.out.println(user.getPostalCode());
	        pstmt.setString(11, user.getCountry());
	        pstmt.setString(12, id);
	        count = pstmt.executeUpdate();
	        // Step 6: Process Result
	        if (count > 0)
	            System.out.println(count + " records updated");

	        // Step 7: Close connection
	        conn.close();
	    } catch (Exception e) {
	        System.out.println("Error: " + e);
	    }
	    return count;
	}

	public int deleteUser(String id) {
	    Connection conn = null;
	    int count = 0;

	    try {
	        conn = DBConnection.getConnection();
	        // SQL command
	        String deleteStr = "DELETE FROM jad.members WHERE id=?";
	        // Prepare statement
	        PreparedStatement pstmt = conn.prepareStatement(deleteStr);
	        // Set the values
	        pstmt.setString(1, id);
	        count = pstmt.executeUpdate();
	        // Step 6: Process Result
	        if (count > 0)
	            System.out.println(count + " records deleted");

	        // Step 7: Close connection
	        conn.close();
	    } catch (Exception e) {
	        System.out.println("Error: " + e);
	    }
	    return count;
	}


}
