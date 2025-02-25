package MovieTicketBooking;

import java.sql.SQLException;

import javax.sql.RowSet;

public class AvailableShowsSeatsFilter implements javax.sql.rowset.Predicate{
	private int minSeats; 
	
	public AvailableShowsSeatsFilter(int minSeats) {
//		super();
		this.minSeats = minSeats;
	}

	@Override
	public boolean evaluate(RowSet rs) {
		// TODO Auto-generated method stub
		
		try {
			int availableSeats = rs.getInt("available_seats");
			return availableSeats >= minSeats;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean evaluate(Object value, int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean evaluate(Object value, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
