package BookMyShow;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

//import MovieTicketBooking.DatabaseConnection;

public class AdminControl {
	
	public static void insertMovies(Scanner sc) throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		if(conn==null) {
			System.out.println("Database Connection Failed");
			System.out.println("================================");
            System.out.println("");
			return;
		}
		
		String insertMovieSQL = "Insert into Movies (title, genre, duration) Values(?,?,?);";
		
//		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie Name: ");
		String name = sc.nextLine();
		System.out.println("Enter Movie Genre: ");
		String genre = sc.nextLine();
		System.out.println("Enter Movie Runtime: ");
		int duration = sc.nextInt();
		try(PreparedStatement psmt = conn.prepareStatement(insertMovieSQL)) {
			//movie1
			psmt.setString(1, name);
			psmt.setString(2, genre);
			psmt.setInt(3, duration);
			psmt.executeUpdate();
			
			System.out.println("Movies data inserted successfully!");
			System.out.println("================================");
            System.out.println("");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void insertShows(Scanner sc) {
		Connection conn = DatabaseConnection.getConnection();
		if(conn==null) {
			System.out.println("Database Connection Failed");
			System.out.println("================================");
            System.out.println("");
			return;
		}
		
		
		
		String insertShowSQL = "Insert into Shows (movie_id, show_time, available_seats) Values(?,?,?);";
//        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter movie_id: ");
        int movieId = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.println("Enter Show Date and Time (YYYY-MM-DD HH:MM): ");
        String showDateTime = sc.nextLine();
        Timestamp showTimestamp = Timestamp.valueOf(LocalDateTime.parse(showDateTime.replace(" ", "T")));

        System.out.println("Enter available seats: ");
        int availableSeats = sc.nextInt();
        sc.nextLine(); // Consume newline
        
        try (PreparedStatement psmt = conn.prepareStatement(insertShowSQL)) {
            psmt.setInt(1, movieId);
            psmt.setTimestamp(2, showTimestamp);
            psmt.setInt(3, availableSeats);
            psmt.executeUpdate();
            
            System.out.println("Show data inserted successfully");
            System.out.println("================================");
            System.out.println("");
		}catch(SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
            System.out.println("Invalid input format.");
            System.out.println("================================");
            System.out.println("");
        }
		
	}
	
	public static void insertUser(Scanner sc) {
	    Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        System.out.println("================================");
            System.out.println("");
	        return;
	    }

	    String insertUserSQL = "INSERT INTO users (username, password) VALUES (?, ?);";
//	    Scanner sc = new Scanner(System.in);
	    sc.nextLine();
	    System.out.println("Enter username: ");
	    String username = sc.nextLine();

	    System.out.println("Enter password: ");
	    String password = sc.nextLine();
	    
	    try (PreparedStatement psmt = conn.prepareStatement(insertUserSQL)) {
	        psmt.setString(1, username);
	        psmt.setString(2, password);
	        psmt.executeUpdate();
	        
	        System.out.println("User Created successfully");
	        System.out.println("================================");
            System.out.println("");
	    } catch (SQLIntegrityConstraintViolationException e) {
	        System.out.println("Username already exist!");
	        System.out.println("================================");
            System.out.println("");
	    }catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("Invalid input format.");
	        System.out.println("================================");
            System.out.println("");
	    } 
	}
	public static void insertAdmin(Scanner sc) {
		Connection conn = DatabaseConnection.getConnection();
		if (conn == null) {
			System.out.println("Database Connection Failed");
			System.out.println("================================");
			System.out.println("");
			return;
		}
		
		String insertUserSQL = "INSERT INTO users (username, password, is_admin) VALUES (?, ?, ?);";
//	    Scanner sc = new Scanner(System.in);
		sc.nextLine();
		System.out.println("Enter username: ");
		String username = sc.nextLine();
		
		System.out.println("Enter password: ");
		String password = sc.nextLine();
		
		try (PreparedStatement psmt = conn.prepareStatement(insertUserSQL)) {
			psmt.setString(1, username);
			psmt.setString(2, password);
			psmt.setBoolean(3, true);
			psmt.executeUpdate();
			
			System.out.println("User Created successfully");
			System.out.println("================================");
			System.out.println("");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Username already exist!");
			System.out.println("================================");
			System.out.println("");
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Invalid input format.");
			System.out.println("================================");
			System.out.println("");
		} 
	}


	
	public static void ViewAllUsers() { // Pass scanner as parameter
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            System.out.println("================================");
            System.out.println("");
            return;
        }

        String query = "SELECT * FROM users;";

        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            ResultSet rs = psmt.executeQuery();

            System.out.println("User Details:");
            System.out.println("===============================================================================================================================");
            // Print column headers dynamically
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println("\n===============================================================================================================================");

            // Print each row of the result set
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            System.out.println("===============================================================================================================================");
            rs.close(); // Closing the ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	

//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		insertAdmin(sc);
//	}
}
