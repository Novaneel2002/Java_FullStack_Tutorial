package MovieTicketBooking;

import java.sql.*;

public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/movie_booking";
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






/* Movies -> title, genre, duration
 * Shows -> 1 -> FK -> Movies
 * Bookings -> id, name, show, seats, date -> FK -> Shows */
