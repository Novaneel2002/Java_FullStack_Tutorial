package MovieTicketBooking;

import java.sql.*;

public class DatabaseTables {
	public static void createTable() {
		Connection conn = DatabaseConnection.getConnection();
		if(conn == null) {
			System.out.println("Database Connection failed.");
			return;
			
		}
		try {
			Statement smt = conn.createStatement();
			String createMoviesTable = "Create Table If not exists Movies("
									   + "movie_id INT AUTO_INCREMENT PRIMARY KEY,"
									   + "title varchar(60) NOT NULL,"
									   + "genre varchar(20),"
									   + "duration INT NOT NULL);";
			
			smt.executeUpdate(createMoviesTable);
			System.out.println("Movies Table Created Successfully!");
			
			//
			String createShowTable = "CREATE TABLE IF NOT EXISTS Shows("
			           + "show_id INT AUTO_INCREMENT PRIMARY KEY, "
			           + "movie_id INT, "
			           + "show_time DATETIME NOT NULL, "
			           + "available_seats INT NOT NULL, "
			           + "FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)"
			           + ");";
			
			
			smt.executeUpdate(createShowTable);
			System.out.println("Shows Table Created Successfully!");
			
			String createBookingTable = "CREATE TABLE IF NOT EXISTS Bookings ("
                    + "booking_id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "user_name VARCHAR(50) NOT NULL, "
                    + "show_id INT NOT NULL, "
                    + "seats_booked INT NOT NULL, "
                    + "booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                    + "FOREIGN KEY (show_id) REFERENCES Shows(show_id)"
                    + ");";
			
			
			
			smt.executeUpdate(createBookingTable);
			System.out.println("Shows Table Created Successfully!");
			
			
			
			smt.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
    public static void main(String[] args) {
        createTable();
    }
}
