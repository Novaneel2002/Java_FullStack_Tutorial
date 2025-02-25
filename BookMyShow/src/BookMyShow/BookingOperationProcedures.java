package BookMyShow;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//import MovieTicketBooking.DatabaseConnection;

public class BookingOperationProcedures {
	/* 
	 * View Movies --
	 * View Shows  --
	 * Book Ticket --
	 * View Bookings -- 
	 * Cancel Ticket --
	 */
	
	
	public static void viewMovies() throws SQLException {
	    Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }
	    
	    String procedure = "CREATE PROCEDURE GetAllMovies() "
	            + "BEGIN "
	            + "SELECT * FROM movies; "
	            + "END;";
	                
	    Statement stmt = conn.createStatement();
	    // Execute the stored procedure creation
	    stmt.execute(procedure);
	    System.out.println("ViewMovies created successfully.");
	}
	public static void viewShows() throws SQLException {
	    Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }
	    
	    String procedure = "CREATE PROCEDURE GetShowsByMovieName(IN movieName VARCHAR(255)) "
	            + "BEGIN "
	            + "SELECT movies.title, shows.show_id, shows.show_time, shows.available_seats "
	            + "FROM shows "
	            + "JOIN movies ON shows.movie_id = movies.movie_id "
	            + "WHERE movies.title = movieName; "
	            + "END;";
	                
	    Statement stmt = conn.createStatement();
	    // Execute the stored procedure creation
	    stmt.execute(procedure);
	    System.out.println("ViewShows created successfully.");
	}

	
	public static void BookTicketProc() throws SQLException {
	    Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }
	    
	    String createBookTicketProcedure = "CREATE PROCEDURE BookTicket("
	            + "IN username VARCHAR(255), "
	            + "IN showId INT, "
	            + "IN reqSeats INT, "
	            + "OUT bookingStatus VARCHAR(255)) "
	            + "BEGIN "
	            + "DECLARE availableSeats INT; "
	            + "SELECT available_seats INTO availableSeats FROM shows WHERE show_id = showId; "
	            + "IF availableSeats >= reqSeats THEN "
	            + "UPDATE shows SET available_seats = availableSeats - reqSeats WHERE show_id = showId; "
	            + "INSERT INTO bookings(user_name, show_id, seats_booked) VALUES (username, showId, reqSeats); "
	            + "SET bookingStatus = 'Seats Booked successfully!'; "
	            + "ELSE "
	            + "SET bookingStatus = 'Not enough seats available!'; "
	            + "END IF; "
	            + "END;";
	    
	    try (Statement stmt = conn.createStatement()) {
	        stmt.execute(createBookTicketProcedure);
	        System.out.println("BookTicket created successfully.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void ViewAllBookingsProc() throws SQLException {
	    Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }

	    String createViewAllBookingsProcedure = "CREATE PROCEDURE GetAllBookings() "
	            + "BEGIN "
	            + "SELECT bookings.user_name, bookings.Booking_id, movies.title, shows.show_time, bookings.seats_booked "
	            + "FROM movies "
	            + "JOIN shows ON movies.movie_id = shows.movie_id "
	            + "JOIN bookings ON shows.show_id = bookings.show_id; "
	            + "END;";

	    try (Statement stmt = conn.createStatement()) {
	        stmt.execute(createViewAllBookingsProcedure);
	        System.out.println("ViewAllBookings created successfully.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public static void ViewBookingProc() throws SQLException {
	    Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }
	    
	    String createViewBookingProcedure = "CREATE PROCEDURE GetUserBookings("
	            + "IN username VARCHAR(255)) "
	            + "BEGIN "
	            + "SELECT bookings.user_name, bookings.Booking_id, movies.title, shows.show_time, bookings.seats_booked "
	            + "FROM movies "
	            + "JOIN shows ON movies.movie_id = shows.movie_id "
	            + "JOIN bookings ON shows.show_id = bookings.show_id "
	            + "WHERE bookings.user_name = username; "
	            + "END;";
	    
	    try (Statement stmt = conn.createStatement()) {
	        stmt.execute(createViewBookingProcedure);
	        System.out.println("ViewBooking created successfully.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public static void CancelBookingProc() throws SQLException {
	    Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }

	    String createCancelBookingProcedure = "CREATE PROCEDURE CancelBooking("
	            + "IN username VARCHAR(255), "
	            + "IN bookingId INT, "
	            + "OUT status VARCHAR(255)) "
	            + "BEGIN "
	            + "DECLARE showId INT; "
	            + "DECLARE seatsBooked INT; "
	            + "SELECT show_id, seats_booked INTO showId, seatsBooked FROM bookings WHERE user_name = username AND booking_id = bookingId; "
	            + "IF showId IS NOT NULL THEN "
	            + "UPDATE shows SET available_seats = available_seats + seatsBooked WHERE show_id = showId; "
	            + "DELETE FROM bookings WHERE booking_id = bookingId; "
	            + "SET status = 'Booking cancelled successfully!'; "
	            + "ELSE "
	            + "SET status = 'Booking not found.'; "
	            + "END IF; "
	            + "END;";

	    try (Statement stmt = conn.createStatement()) {
	        stmt.execute(createCancelBookingProcedure);
	        System.out.println("CancelBooking created successfully.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	

	public static void main(String[] args) throws SQLException {
		viewMovies();
		viewShows();
		BookTicketProc();
		ViewBookingProc();
		CancelBookingProc();
		ViewAllBookingsProc();
	}
	
	
}
