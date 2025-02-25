package BookMyShow;
import java.util.*;
import java.sql.*;
public class LogingIn {
    public static boolean verifyUser(String username, String password) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return false;
        }

        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?;";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, username);
            psmt.setString(2, password);
            ResultSet rs = psmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            } else {
             
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public static void Login(Scanner sc) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        sc.nextLine();
        System.out.println("Enter Username: ");
        String user_name = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        
        if (verifyUser(user_name, password)) {
            String query = "SELECT IS_admin FROM users WHERE username = ?;";
            try (PreparedStatement psmt = conn.prepareStatement(query)) {
                psmt.setString(1, user_name);
                ResultSet rs = psmt.executeQuery();
                
                if (rs.next()) { // Move the cursor to the first row
                    if (rs.getBoolean("IS_admin")) {
                    	System.out.println("");
                    	System.out.println("Logging in as Admin");
                    	System.out.println("");
                        AdminView.menu();
                    } else {
                    	System.out.println("");
                    	System.out.println("Logging in as User");
                        UserView.menu(user_name);
                        System.out.println("");
                    }
                } else {
                    System.out.println("User not found");
                    System.out.println("================================");
                }
            }
        } else {
            System.out.println("Wrong Username or Password");
            System.out.println("================================");
        }
    }

}
