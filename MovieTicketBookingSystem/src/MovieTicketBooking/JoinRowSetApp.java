package MovieTicketBooking;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class JoinRowSetApp {
    public static void main(String[] args) throws IOException {

        Connection conn = DatabaseConnection.getConnection();
        try {
        	//Table1
            WebRowSet wrsMovies = RowSetProvider.newFactory().createWebRowSet();
            wrsMovies.setCommand("select movie_id, title, genre, duration from movies");
            wrsMovies.execute(conn);
            //Table2
            WebRowSet wrsShows = RowSetProvider.newFactory().createWebRowSet();
            wrsShows.setCommand("select show_id,movie_id, available_seats from shows");
            wrsShows.execute(conn);

            conn.close();
            System.out.println("Connection Closed");

            JoinRowSet jrs = RowSetProvider.newFactory().createJoinRowSet();
            jrs.addRowSet(wrsMovies, "movie_id");
            jrs.addRowSet(wrsShows, "movie_id");


            FileWriter writer = new FileWriter("movies_shows.xml");
            jrs.writeXml(writer);
            writer.close();


            JoinRowSet new_jrs = RowSetProvider.newFactory().createJoinRowSet();
            FileReader reader = new FileReader("movies_shows.xml");
            new_jrs.readXml(reader);
            reader.close();
            new_jrs.afterLast();
    
            while (new_jrs.previous()) {
                System.out.println("Movie ID : " + new_jrs.getInt("movie_id")
                    + " || Title : " + new_jrs.getString("title")
                    + " || Show ID : " + new_jrs.getInt("show_id")
                    + " || Genre : " + new_jrs.getString("genre")
                    + " || Available Seats : " + new_jrs.getString("available_seats")
                    + " || Duration : " + new_jrs.getString("duration"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
