package MovieTicketBooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
public class ResultSetMetaDataApp {
	public static void main(String[] args) {
		try(Connection conn = DatabaseConnection.getConnection();
				Statement smt = conn.createStatement();
				ResultSet rs = smt.executeQuery("Select * from shows;")){
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int colCount = rsmd.getColumnCount();
			System.out.println(colCount);
			
			for(int i=1; i<=colCount;i++) {
				System.out.println("Column: " + i);
				System.out.println("Column Name: " + rsmd.getColumnName(i));
				System.out.println("Column Label: " + rsmd.getColumnLabel(i));
				System.out.println("Column Type: " + rsmd.getColumnTypeName(i));
				System.out.println("Column Class: " + rsmd.getColumnClassName(i));
				System.out.println("Column IS AUTOINCREMENT: " + rsmd.isAutoIncrement(i));
				System.out.println("Column Precision: " + rsmd.getPrecision(i));
				System.out.println("Column DisplaySize: " + rsmd.getColumnDisplaySize(i));
				System.out.println("Column Table Name: " + rsmd.getTableName(i));
				System.out.println("Column Schema Name: " + rsmd.getSchemaName(i));
				System.out.println("========================================================");
				
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
