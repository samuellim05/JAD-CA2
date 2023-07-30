package com.example.bookstore_ws.dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionDAO {
	public boolean checkPromoExist(String promo_code) throws SQLException {
		boolean exist = false;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * FROM promotion WHERE promocode=?";
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			ps.setString(1, promo_code);
			ResultSet rs = ps.executeQuery();
			exist = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return exist;
	}

	public Promotion getPromoDetails(String promo_code) throws SQLException {
		Promotion promotion = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * FROM promotion WHERE promocode=?";
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			ps.setString(1, promo_code);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				promotion = new Promotion();
				promotion.setPromoid(rs.getInt("promoid"));
				promotion.setPromocode(rs.getString("promoCode"));
				promotion.setStart_date(rs.getString("start_date"));
				promotion.setEnd_date(rs.getString("end_date"));
				promotion.setDiscount(rs.getDouble("discount"));
				promotion.setMax_usage(rs.getInt("max_usage"));
				promotion.setCatid(rs.getInt("catid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return promotion;
	}
	
	public boolean deletePromo(int pid) throws SQLException {
		boolean success = false;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "DELETE FROM promotion WHERE promoid=?";
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			ps.setInt(1, pid);
			int rowsAffected = ps.executeUpdate();
			success = rowsAffected > 0;
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return success;
	}

	
}
