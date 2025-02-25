package MovieTicketBooking;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
//import com.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DatabaseMetaDataApp {
	public static void main(String[] args) throws SQLException {
		try(Connection conn = DatabaseConnection.getConnection()){
			DatabaseMetaData dbMd =  conn.getMetaData();
			
			System.out.println("Database Product Name: " + dbMd.getDatabaseProductName());
			System.out.println("Database Product Version: " + dbMd.getDatabaseProductVersion());
			System.out.println("Database Driver Name: " + dbMd.getDriverName());
			System.out.println("Database Driver Version: " + dbMd.getDriverVersion());
			System.out.println("Database URL: " + dbMd.getURL());
			System.out.println("Database Username: " + dbMd.getUserName());
			
			
			
			ResultSet rs = dbMd.getTables("movie_booking", null, "%", new String[] {"TABLE"});
			while(rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
			}
			
			
			// Retrieve columns of the "shows" table
            ResultSet rs2 = dbMd.getColumns("movie_booking", null, "shows", "%");
            System.out.println("\nColumns in the 'shows' table:");
            while (rs2.next()) {
                System.out.println("Column Name: " + rs2.getString("COLUMN_NAME"));
                System.out.println("Column Type: " + rs2.getString("TYPE_NAME"));
                System.out.println("---------------------------");
            }
 
            // Retrieve primary keys of the "shows" table
            ResultSet rs3 = dbMd.getPrimaryKeys("movie_booking", null, "shows");
            while (rs3.next()) {
                System.out.println("Primary Key Column: " + rs3.getString("COLUMN_NAME"));
                System.out.println("---------------------------");
            }
 
            // Check support for transactions
            System.out.println("Supports Transactions: " + dbMd.supportsTransactions());
 
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
