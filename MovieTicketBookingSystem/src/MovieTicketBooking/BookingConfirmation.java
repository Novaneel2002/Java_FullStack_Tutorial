package MovieTicketBooking;

import java.util.*;
import java.sql.*;

public class BookingConfirmation {
    public static void ViewBooking(Scanner sc) { // Pass scanner as parameter
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }

        System.out.println("Enter username: ");
        String name = sc.nextLine();
        System.out.println("\n\n");
        String Bookingquery = "SELECT bookings.user_name, bookings.Booking_id, movies.title, shows.show_time, bookings.seats_booked " +
                              "FROM movies " +
                              "JOIN shows ON movies.movie_id = shows.movie_id " +
                              "JOIN bookings ON shows.show_id = bookings.show_id " +
                              "WHERE bookings.user_name = ?;";

        try (PreparedStatement psmt = conn.prepareStatement(Bookingquery)) {
            psmt.setString(1, name);
            ResultSet rs = psmt.executeQuery();
            
            System.out.println("Booking Details:");
            System.out.println("===============================================================================================================================");
            System.out.printf("%-15s %-15s %-30s %-20s %-10s%n", "Username", "Booking ID", "Movie Title", "Show Time", "Seats Booked");
            System.out.println("===============================================================================================================================");

            while (rs.next()) {
                String User_name = rs.getString("user_name");
                int Booking_id = rs.getInt("Booking_id");
                String title = rs.getString("title");
                Timestamp showtime = rs.getTimestamp("show_time");
                int seats = rs.getInt("seats_booked");

                System.out.printf("%-15s %-15d %-30s %-20s %-10d%n", User_name, Booking_id, title, showtime, seats);
            }

            System.out.println("================================================================================================================================");
            rs.close(); // Closing the ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ViewBooking(sc);
        sc.close(); // Closing the Scanner in the main method
    }
}
