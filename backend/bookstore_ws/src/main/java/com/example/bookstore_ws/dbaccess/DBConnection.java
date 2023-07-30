package com.example.bookstore_ws.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() {
		String dbUrl = "jdbc:mysql://aws.connect.psdb.cloud/jad";
		String dbUser = "ade00m6m7fjwiydwr4o1";
		String dbPassword = "pscale_pw_w3m3RVv1nE2cSnC7WO2BaT0XV6I2vqwsth4d5HlZrKF";
		String dbClass = "com.mysql.jdbc.Driver";
		
		Connection connection = null;
		try {
			Class.forName(dbClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}

