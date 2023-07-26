package com.connectDatabase;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class databaseConnection {
	private final static String dataBaseName = "jdbc:mysql://localhost:3306/firstjavaproject";
    private final static String name = "root";
	private final static String password = "142573689";
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dataBaseName, name, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
