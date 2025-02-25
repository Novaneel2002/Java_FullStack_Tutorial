package BookMyShow;

import java.sql.SQLException;
import java.util.Scanner;


public class UserView {
	
	public static void menu(String username) throws SQLException {
        Scanner sc = new Scanner(System.in);
        while(true) {
        	System.out.println("");
            System.out.println("Welcome to Movie Ticket Booking System");
            System.out.println("Enter your choice: ");
            System.out.println("Press 1: View All Movies");
            System.out.println("Press 2: View Shows");
            System.out.println("Press 3: Book Ticket");
            System.out.println("Press 4: Show All Bookings");
            System.out.println("Press 5: Cancel Booking");
            System.out.println("Press 6: Log out");
            System.out.println("================================");
            System.out.println("");
            int n = sc.nextInt();
            sc.nextLine();
            switch(n) {
            case 1:
                BookingOperations.CallviewMovies();
                break;
            case 2:
            	BookingOperations.CallviewShows(sc);
                break;
            case 3:
            	BookingOperations.CallBookTicket(username,sc);
            	break;
            case 4:
            	BookingOperations.CallViewBooking(username);
            	break;
            case 5:
            	BookingOperations.CallCancelBooking(username,sc);
            	break;
            case 6:
            	System.out.println("Logging out!");
                return;
            default:
                System.out.println("Enter valid choice (1-6): ");
                System.out.println("================================");
                System.out.println("");
            }
        }
	}

}
