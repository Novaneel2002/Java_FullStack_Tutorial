package MovieTicketBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookingCancellation {

    public static void ticketCancel() {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        // Taking input from user
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        // Fetching all bookings for the user
        String fetchBookingsQuery = "SELECT booking_id, show_id, seats_booked FROM bookings WHERE user_name = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(fetchBookingsQuery)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Bookings for " + username + ":");
            System.out.println("================================================================================================================================");            System.out.printf("%-15s %-15s %-15s%n", "Booking ID", "Show ID", "Seats Booked");
            System.out.println("================================================================================================================================");
            while (rs.next()) {
                int bookingId = rs.getInt("booking_id");
                int showId = rs.getInt("show_id");
                int seatsBooked = rs.getInt("seats_booked");
                System.out.printf("%-15d %-15d %-15d%n", bookingId, showId, seatsBooked);
            }

            System.out.println("================================================================================================================================");        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Taking booking ID input from user
        System.out.print("Enter the booking ID you want to cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        String cancellationQuery = "SELECT booking_id, show_id, seats_booked FROM bookings WHERE user_name = ? AND booking_id = ?;";
        int showId = 0;
        int seatsBooked = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(cancellationQuery)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, bookingId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                showId = rs.getInt("show_id");
                seatsBooked = rs.getInt("seats_booked");
            } else {
                System.out.println("Booking not found.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Update seats in shows table
        String updateSeatsQuery = "UPDATE shows SET available_seats = available_seats + ? WHERE show_id = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(updateSeatsQuery)) {
            pstmt.setInt(1, seatsBooked);
            pstmt.setInt(2, showId);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Available seats updated successfully!");
            } else {
                System.out.println("Failed to update available seats.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Delete movie booking from bookings table
        String deleteBookingQuery = "DELETE FROM bookings WHERE booking_id = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteBookingQuery)) {
            pstmt.setInt(1, bookingId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Booking cancelled successfully!");
            } else {
                System.out.println("Failed to cancel the booking.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
