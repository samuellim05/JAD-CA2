package com.example.bookstore_ws.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() {
		String dbUrl = "jdbc:mysql://aws.connect.psdb.cloud/jad";
		String dbUser = "trorkbipifbm4f8sysqo";
		String dbPassword = "pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM";
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

