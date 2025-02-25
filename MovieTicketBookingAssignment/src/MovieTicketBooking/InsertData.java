package MovieTicketBooking;

import java.sql.*;
import java.time.LocalDateTime;

public class InsertData {
	
	
	public static void insertMovies() throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		if(conn==null) {
			System.out.println("Database Connection Failed");
			return;
		}
		
		String insertMovieSQL = "Insert into Movies (title, genre, duration) Values(?,?,?);";
		
		
		try(PreparedStatement psmt = conn.prepareStatement(insertMovieSQL)) {
			//movie1
			psmt.setString(1, "Fighter");
			psmt.setString(2, "Action");
			psmt.setInt(3, 150);
			psmt.executeUpdate();
			
			//movie2
			psmt.setString(1, "Rokstar");
			psmt.setString(2, "Romance");
			psmt.setInt(3, 120);
			psmt.executeUpdate();
			
			//movie3
			psmt.setString(1, "Intersteller");
			psmt.setString(2, "Thriller");
			psmt.setInt(3, 170);
			psmt.executeUpdate();
			
			
			System.out.println("Movies data inserted successfully!");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	//insert Shows
	public static void insertShows() {
		Connection conn = DatabaseConnection.getConnection();
		if(conn==null) {
			System.out.println("Database Connection Failed");
			return;
		}
		
		
		
		String insertShowSQL = "Insert into Shows (movie_id, show_time, available_seats) Values(?,?,?);";

		try(PreparedStatement psmt = conn.prepareStatement(insertShowSQL)){
			//show1
			psmt.setInt(1, 1);
			psmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(2025,2,14, 18,30)));
			psmt.setInt(3, 100);
			psmt.executeUpdate();
			//show1
			psmt.setInt(1, 2);
			psmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(2025,2,13, 13,30)));
			psmt.setInt(3, 120);
			psmt.executeUpdate();
			//show1
			psmt.setInt(1, 3);
			psmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(2025,2,12, 16,30)));
			psmt.setInt(3, 90);
			psmt.executeUpdate();
			
			
			
			System.out.println("Shows data inserted successfully");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
        insertMovies();
        insertShows();
    }
	
	
}
