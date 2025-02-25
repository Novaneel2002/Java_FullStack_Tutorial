package BookMyShow;

import java.sql.*;

public class DatabaseConnection {
	private static final String URL = "";
	private static final String USER = "";
	private static final String PASSWORD = "";
	private static Connection connection;
	
	
	public static Connection getConnection() {
		if(connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
//				System.out.println("Database Connection created successfully!");
			} catch (SQLException | ClassNotFoundException e) {
				 e.printStackTrace();
			}
		}
		
		
		return connection;
	}
	public static void main(String[] args) {
		getConnection();
	}
	
}
