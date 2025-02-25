package BookMyShow;
import java.util.*;
import java.sql.*;
public class BookingOperations {
	
	public static void CallviewMovies() {
	    Connection conn = null;
	    CallableStatement cstmt = null;
	    try {
	        conn = DatabaseConnection.getConnection();
	        if (conn == null) {
	            System.out.println("Database Connection Failed");
	            System.out.println("================================");
                System.out.println("");
	            return;
	        }

	        // Calling the stored procedure
	        cstmt = conn.prepareCall("{CALL GetAllMovies()}");

	        // Execute the stored procedure
	        ResultSet rs = cstmt.executeQuery();

	        // Print the output values
	        System.out.println("All Available Movies:");
	        System.out.println("==============================================================================================================================");
	        System.out.printf("%-10s %-30s %-20s %-10s%n", "Movie ID", "Movie Title", "Genre", "Duration (minutes)");
	        System.out.println("==============================================================================================================================");

	        while (rs.next()) {
	            int movieid = rs.getInt("movie_id");
	            String title = rs.getString("title");
	            String genre = rs.getString("genre");
	            int duration = rs.getInt("duration");

	            System.out.printf("%-10d %-30s %-20s %-10d%n", movieid, title, genre, duration);
	        }

	        System.out.println("==============================================================================================================================");

	        rs.close(); // Close the ResultSet
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	}


	
	
	
	
	public static void CallviewShows(Scanner sc) {
	    Connection conn = null;
	    CallableStatement cstmt = null;
	    try {
	        conn = DatabaseConnection.getConnection();
	        if (conn == null) {
	            System.out.println("Database Connection Failed");
	            return;
	        }

	        // Calling the stored procedure
	        cstmt = conn.prepareCall("{CALL GetShowsByMovieName(?)}");

	        // Set the input parameter
	        System.out.print("Enter the movie name: ");
//	        Scanner scanner = new Scanner(System.in);
	        String movieName = sc.nextLine();
	        cstmt.setString(1, movieName);

	        // Execute the stored procedure
	        ResultSet rs = cstmt.executeQuery();

	        // Print the output values
	        System.out.println("Shows for " + movieName + ":");
	        System.out.println("==============================================================================================================================");
	        System.out.printf("%-30s %-10s %-20s %-15s%n", "Movie Name", "Show ID", "Show Time", "Available Seats");
	        System.out.println("==============================================================================================================================");

	        while (rs.next()) {
	            String title = rs.getString("title");
	            int showId = rs.getInt("show_id");
	            Timestamp showTime = rs.getTimestamp("show_time");
	            int availableSeats = rs.getInt("available_seats");

	            System.out.printf("%-30s %-10d %-20s %-15d%n", title, showId, showTime, availableSeats);
	        }

	        System.out.println("==============================================================================================================================");

	        rs.close(); // Close the ResultSet
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	
	//For User
	public static void CallBookTicket(String username, Scanner sc) {
	    Connection conn = null;
	    CallableStatement cstmt = null;
	    try {
	        conn = DatabaseConnection.getConnection();
	        if (conn == null) {
	            System.out.println("Database Connection Failed");
	            return;
	        }

	        // Calling the stored procedure
	        cstmt = conn.prepareCall("{CALL BookTicket(?, ?, ?, ?)}");

	        // Set the input parameters
//	        Scanner scanner = new Scanner(System.in);
//	        System.out.print("Enter your username: ");
//	        String username = scanner.nextLine();
	        System.out.print("Enter the show ID: ");
	        int showId = sc.nextInt();
	        System.out.print("Enter the number of seats to book: ");
	        int reqSeats = sc.nextInt();
	        sc.nextLine(); // Clear the buffer

	        cstmt.setString(1, username);
	        cstmt.setInt(2, showId);
	        cstmt.setInt(3, reqSeats);

	        // Register the output parameter
	        cstmt.registerOutParameter(4, Types.VARCHAR);

	        // Execute the stored procedure
	        cstmt.execute();

	        // Retrieve the output parameter value
	        String bookingStatus = cstmt.getString(4);

	        // Print the output value
	        System.out.println(bookingStatus);
	        System.out.println("================================");
            System.out.println("");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public static void CallViewBooking(String username) {
	    Connection conn = null;
	    CallableStatement cstmt = null;
	    try {
	        conn = DatabaseConnection.getConnection();
	        if (conn == null) {
	            System.out.println("Database Connection Failed");
	            return;
	        }

	        // Calling the stored procedure
	        cstmt = conn.prepareCall("{CALL GetUserBookings(?)}");

	        // Set the input parameter
//	        System.out.print("Enter your username: ");
//	        Scanner scanner = new Scanner(System.in);
//	        String username = scanner.nextLine();
	        cstmt.setString(1, username);

	        // Execute the stored procedure
	        ResultSet rs = cstmt.executeQuery();

	        // Print the output values
	        System.out.println("Booking Details:");
	        System.out.println("==============================================================================================================================");
	        System.out.printf("%-15s %-15s %-30s %-20s %-10s%n", "Username", "Booking ID", "Movie Title", "Show Time", "Seats Booked");
	        System.out.println("==============================================================================================================================");

	        while (rs.next()) {
	            String user_name = rs.getString("user_name");
	            int booking_id = rs.getInt("Booking_id");
	            String title = rs.getString("title");
	            Timestamp show_time = rs.getTimestamp("show_time");
	            int seats_booked = rs.getInt("seats_booked");

	            System.out.printf("%-15s %-15d %-30s %-20s %-10d%n", user_name, booking_id, title, show_time, seats_booked);
	        }

	        System.out.println("==============================================================================================================================");

	        rs.close(); // Close the ResultSet
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    
	}
	
	
	public static void CallCancelBooking(String username, Scanner sc) {
	    Connection conn = null;
	    CallableStatement cstmt = null;
	    try {
	        conn = DatabaseConnection.getConnection();
	        if (conn == null) {
	            System.out.println("Database Connection Failed");
	            return;
	        }

	        // Calling the stored procedure
	        cstmt = conn.prepareCall("{CALL CancelBooking(?, ?, ?)}");

	        // Set the input parameters
//	        Scanner scanner = new Scanner(System.in);
//	        System.out.print("Enter your username: ");
//	        String username = scanner.nextLine();
	        CallViewBooking(username);
	        System.out.print("Enter the booking ID to cancel: ");
	        int bookingId = sc.nextInt();
	        sc.nextLine(); // Clear the buffer

	        cstmt.setString(1, username);
	        cstmt.setInt(2, bookingId);

	        // Register the output parameter
	        cstmt.registerOutParameter(3, Types.VARCHAR);

	        // Execute the stored procedure
	        cstmt.execute();

	        // Retrieve the output parameter value
	        String status = cstmt.getString(3);

	        // Print the output value
	        System.out.println(status);
	        System.out.println("================================");
            System.out.println("");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	// For Admin
	public static void CallBookTicket(Scanner sc) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DatabaseConnection.getConnection();
			if (conn == null) {
				System.out.println("Database Connection Failed");
				return;
			}
			
			// Calling the stored procedure
			cstmt = conn.prepareCall("{CALL BookTicket(?, ?, ?, ?)}");
			
			// Set the input parameters
//			Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter username: ");
	        String username = sc.nextLine();
			System.out.print("Enter the show ID: ");
			int showId = sc.nextInt();
			System.out.print("Enter the number of seats to book: ");
			int reqSeats = sc.nextInt();
			sc.nextLine(); // Clear the buffer
			
			cstmt.setString(1, username);
			cstmt.setInt(2, showId);
			cstmt.setInt(3, reqSeats);
			
			// Register the output parameter
			cstmt.registerOutParameter(4, Types.VARCHAR);
			
			// Execute the stored procedure
			cstmt.execute();
			
			// Retrieve the output parameter value
			String bookingStatus = cstmt.getString(4);
			
			// Print the output value
			System.out.println(bookingStatus);
			System.out.println("================================");
            System.out.println("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	public static void CallViewBooking() {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DatabaseConnection.getConnection();
			if (conn == null) {
				System.out.println("Database Connection Failed");
				return;
			}
			
			// Calling the stored procedure
			cstmt = conn.prepareCall("{CALL GetUserBookings(?)}");
			
			// Set the input parameter
	        System.out.print("Enter your username: ");
	        Scanner scanner = new Scanner(System.in);
	        String username = scanner.nextLine();
			cstmt.setString(1, username);
			
			// Execute the stored procedure
			ResultSet rs = cstmt.executeQuery();
			
			// Print the output values
			System.out.println("Booking Details:");
			System.out.println("==============================================================================================================================");
			System.out.printf("%-15s %-15s %-30s %-20s %-10s%n", "Username", "Booking ID", "Movie Title", "Show Time", "Seats Booked");
			System.out.println("==============================================================================================================================");
			
			while (rs.next()) {
				String user_name = rs.getString("user_name");
				int booking_id = rs.getInt("Booking_id");
				String title = rs.getString("title");
				Timestamp show_time = rs.getTimestamp("show_time");
				int seats_booked = rs.getInt("seats_booked");
				
				System.out.printf("%-15s %-15d %-30s %-20s %-10d%n", user_name, booking_id, title, show_time, seats_booked);
			}
			
			System.out.println("==============================================================================================================================");
			
			rs.close(); // Close the ResultSet
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

	public static void CallViewAllBookings() {
	    Connection conn = null;
	    CallableStatement cstmt = null;
	    try {
	        conn = DatabaseConnection.getConnection();
	        if (conn == null) {
	            System.out.println("Database Connection Failed");
	            return;
	        }

	        // Calling the stored procedure
	        cstmt = conn.prepareCall("{CALL GetAllBookings()}");

	        // Execute the stored procedure
	        ResultSet rs = cstmt.executeQuery();

	        // Print the output values
	        System.out.println("All Bookings:");
	        System.out.println("==============================================================================================================================");
	        System.out.printf("%-15s %-15s %-30s %-20s %-10s%n", "Username", "Booking ID", "Movie Title", "Show Time", "Seats Booked");
	        System.out.println("==============================================================================================================================");

	        while (rs.next()) {
	            String user_name = rs.getString("user_name");
	            int booking_id = rs.getInt("Booking_id");
	            String title = rs.getString("title");
	            Timestamp show_time = rs.getTimestamp("show_time");
	            int seats_booked = rs.getInt("seats_booked");

	            System.out.printf("%-15s %-15d %-30s %-20s %-10d%n", user_name, booking_id, title, show_time, seats_booked);
	        }

	        System.out.println("==============================================================================================================================");

	        rs.close(); // Close the ResultSet
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	
	public static void CallCancelBooking(Scanner sc) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DatabaseConnection.getConnection();
			if (conn == null) {
				System.out.println("Database Connection Failed");
				return;
			}
			
			// Calling the stored procedure
			cstmt = conn.prepareCall("{CALL CancelBooking(?, ?, ?)}");
			
			// Set the input parameters
//			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter your username: ");
			String username = sc.nextLine();
			CallViewBooking(username);
			System.out.print("Enter the booking ID to cancel: ");
			int bookingId = sc.nextInt();
			sc.nextLine(); // Clear the buffer
			
			cstmt.setString(1, username);
			cstmt.setInt(2, bookingId);
			
			// Register the output parameter
			cstmt.registerOutParameter(3, Types.VARCHAR);
			
			// Execute the stored procedure
			cstmt.execute();
			
			// Retrieve the output parameter value
			String status = cstmt.getString(3);
			
			// Print the output value
			System.out.println(status);
			System.out.println("================================");
            System.out.println("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
