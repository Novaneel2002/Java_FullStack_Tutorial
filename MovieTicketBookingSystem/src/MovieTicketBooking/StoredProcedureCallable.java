package MovieTicketBooking;

import java.util.*;
import java.sql.*;

public class StoredProcedureCallable {
    public static void storedProcedure() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        String procedure = "CREATE PROCEDURE GetMovieTitleAndGenre(IN movieId INT, OUT movietitle VARCHAR(255), OUT movieGenre VARCHAR(255)) "
                         + "BEGIN "
                         + "SELECT title, genre INTO movietitle, movieGenre FROM movies "
                         + "WHERE movie_id = movieId; "
                         + "END;";
                         
        Statement stmt = conn.createStatement();
        // Execute the stored procedure creation
        stmt.execute(procedure);
        System.out.println("Stored procedure created successfully.");
    }
    
    
    public static void ViewUserBookings() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }

        String procedure = "CREATE PROCEDURE GetMovieDetailsByUsername(IN username VARCHAR(255))"
                         + "BEGIN "
                         + "select * from movies join shows on movies.movie_id = shows.movie_id join bookings on bookings.show_id = shows.show_id where user_name = username;"
                         + "END;";
                         
        Statement stmt = conn.createStatement();
        // Execute the stored procedure creation
        stmt.execute(procedure);
        System.out.println("View Bookings procedure created successfully.");
    }

    
    
    
    public static void CancelBookingProc() throws SQLException {
    	Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
 
//        String GetAllBookingsProcedure =
//        		"CREATE PROCEDURE GetAllBookingsByUsername(IN username VARCHAR(255)) " 
//        				+ "BEGIN " 
//        				+  "    SELECT booking_id, show_id, seats_booked, booking_date " 
//        				+  "    FROM bookings " 
//        				+  "    WHERE user_name = username; " 
//        				+   "END; ";
// 
        String createCancelBookingProcedure = "CREATE PROCEDURE CancelBooking(IN bookingId INT) " 
                		+  "BEGIN " 
                		+   "    DECLARE showId INT; " 
                		+    "    DECLARE seatsBooked INT; " 
                +     "SELECT show_id, seats_booked INTO showId, seatsBooked FROM bookings WHERE booking_id = bookingId; " 
                +  "    UPDATE shows SET available_seats = available_seats + seatsBooked WHERE show_id = showId; " 
                +   "    DELETE FROM bookings WHERE booking_id = bookingId; " 
                +  "END; ";
 
        try (Statement stmt = conn.createStatement()) {
//            stmt.execute(GetAllBookingsProcedure);
            stmt.execute(createCancelBookingProcedure);
            System.out.println("Stored procedures created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
//        storedProcedure();
    	
//    	ViewUserBookings();
    	CancelBookingProc();
    }
}
