package MovieTicketBooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.RowSetProvider;

public class FilterApplication {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery("Select * from shows")) {

            FilteredRowSet frs = RowSetProvider.newFactory().createFilteredRowSet();
            frs.populate(rs);

            frs.setFilter(new AvailableShowsSeatsFilter(80));

            while (frs.next()) {
                int showId = frs.getInt("show_id");
                int movieId = frs.getInt("movie_id");
 
                String showTime = frs.getString("show_time");
                int seats = frs.getInt("available_seats");
 
                System.out.println("Show Id: " + showId + " Movie Id: " +  movieId + " Seats: " + seats + " Show Time: " + showTime);
            }	
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
