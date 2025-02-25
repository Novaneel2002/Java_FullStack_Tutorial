package MovieTicketBooking;
import java.util.*;
import java.sql.*;
public class TicketBooking {
	public static void bookticket() throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		if(conn==null) {
			System.out.println("Database Connection Failed");
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name: ");
		String username = sc.next();
		
		System.out.println("");
		ShowAvailableMovies.displayMovies();
		
		System.out.println("Enter show id to Book ticket: ");
		int showid = sc.nextInt();
		
		String query = "select available_seats from shows where show_id = (?);";
		int availableSeats = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, showid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                availableSeats = rs.getInt("available_seats");
//                System.out.println(availableSeats);
            } else {
                System.out.println("Show not found.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        
        System.out.println("Enter number of seats: ");
        int reqseats = sc.nextInt();
        
        String Bookingquery = " insert into bookings(user_name, show_id, seats_Booked) values(?,?,?);";
        String Reduceseats = "UPDATE shows SET available_seats = available_seats - ? WHERE show_id = ?;";
        
        if(reqseats<=availableSeats) {
        	try(PreparedStatement psmt = conn.prepareStatement(Bookingquery)) {
    			//movie1
    			psmt.setString(1, username);
    			psmt.setInt(2, showid);
    			psmt.setInt(3, reqseats);
    			psmt.executeUpdate();
    			
    			System.out.println("Seats Booked successfully!");
    			
    			
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
        	
        	try(PreparedStatement psmt = conn.prepareStatement(Reduceseats)) {
   
        		psmt.setInt(1, reqseats);
                psmt.setInt(2, showid);
                psmt.executeUpdate();
    			
    			
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
        	
        }else {
        	System.out.println("Not enough Seats available!");
        }
        
        
	}
	

}
