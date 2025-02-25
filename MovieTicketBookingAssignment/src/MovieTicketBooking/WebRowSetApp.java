package MovieTicketBooking;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;
public class WebRowSetApp {
	public static void main(String[] args) throws IOException {
		
		Connection conn = DatabaseConnection.getConnection();
		try {
			WebRowSet wrs = RowSetProvider.newFactory().createWebRowSet();
			wrs.setCommand("select * from movies");
			wrs.execute(conn);
			
			conn.close();
			System.out.println("Connection Closed");
			
			FileWriter writer = new FileWriter("movies.xml");
			wrs.writeXml(writer);
			writer.close();
			
			WebRowSet new_wrs = RowSetProvider.newFactory().createWebRowSet();
			FileReader reader = new FileReader("movies.xml");
			new_wrs.readXml(reader);
			reader.close();
			
			while(new_wrs.next()) {
				System.out.println("ID : " + new_wrs.getInt("movie_id")+ "||" + "Title : " + new_wrs.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
