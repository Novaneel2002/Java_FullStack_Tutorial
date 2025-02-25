package MovieTicketBooking;
import java.util.*;
import java.sql.*;
public class ShowAvailableMovies {
    // Movie ticket booking app

    public static void displayMovies() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            // System.out.println("Database Connection Failed");
            return;
        }

        String query = "SELECT * FROM movies JOIN shows ON movies.movie_id = shows.movie_id;";

        try (Statement smt = conn.createStatement()) {
            ResultSet rs = smt.executeQuery(query);

            System.out.println("Available Movies and Shows:");
            System.out.println("================================================================================================================================");
            System.out.printf("%-10s %-30s %-20s %-10s %-25s %-15s%n", "Movie ID", "Title", "Genre", "Duration", "Show Time", "Available Seats");
            System.out.println("================================================================================================================================");
            while (rs.next()) {
                int movieId = rs.getInt("movie_id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");
                Timestamp showtime = rs.getTimestamp("show_time");
                int available_seats = rs.getInt("available_seats");

                System.out.printf("%-10d %-30s %-20s %-10d %-25s %-15d%n", movieId, title, genre, duration, showtime, available_seats);
            }
            System.out.println("================================================================================================================================");        }
    }

    public static void main(String[] args) throws SQLException {
        displayMovies();
    }
}
