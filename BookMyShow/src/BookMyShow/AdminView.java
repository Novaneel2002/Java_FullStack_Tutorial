package BookMyShow;

import java.sql.SQLException;
import java.util.Scanner;


public class AdminView {
	
	public static void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while(true) {
        	System.out.println("");
            System.out.println("Welcome to Movie Ticket Booking System");
            System.out.println("Enter your choice: ");
            System.out.println("Press 1: View All Movies");
            System.out.println("Press 2: Add Movie");
            System.out.println("Press 3: View Shows");
            System.out.println("Press 4: Add Show");
            System.out.println("Press 5: Book Ticket");
            System.out.println("Press 6: Show All Bookings");
            System.out.println("Press 7: Cancel Booking");
            System.out.println("Press 8: Show All Users");
            System.out.println("Press 9: Log out");
            System.out.println("================================");
            System.out.println("");
            int n = sc.nextInt();
            sc.nextLine();
            switch(n) {
            case 1:
                BookingOperations.CallviewMovies();
                break;
            case 2:
                AdminControl.insertMovies(sc);
                break;
            case 3:
            	BookingOperations.CallviewShows(sc);
                break;
            case 4:
                AdminControl.insertShows(sc);
                break;
            case 5:
            	BookingOperations.CallBookTicket(sc);
            	break;
            case 6:
            	BookingOperations.CallViewAllBookings();
            	break;
            case 7:
            	BookingOperations.CallCancelBooking(sc);
            	break;
            case 8:
            	AdminControl.ViewAllUsers();;
            	break;
            case 9:
            	System.out.println("Logging out!");
                return;
            default:
                System.out.println("Enter valid choice (1-9): ");
                System.out.println("================================");
                System.out.println("");
            }
        }
	}

}
