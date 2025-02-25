package MovieTicketBooking;

import java.sql.SQLException;
import java.util.Scanner;

public class MovieTicketBooking{

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Welcome to Movie Ticket Booking System");
            System.out.println("Enter your choice: ");
            System.out.println("Press 1: Show All Shows");
            System.out.println("Press 2: Book Ticket");
            System.out.println("Press 3: Show Bookings");
            System.out.println("Press 4: Cancel Booking");
            System.out.println("Press 5: Exit");
            int n = sc.nextInt();
            sc.nextLine();
            switch(n) {
            case 1:
                ShowAvailableMovies.displayMovies();
                break;
            case 2:
                TicketBooking.bookticket();
                break;
            case 3:
                BookingConfirmation.ViewBooking(sc);
                break;
            case 4:
                BookingCancellation.ticketCancel();
                break;
            case 5:
                System.out.println("Exiting from the system!");
                sc.close();
                return;
            default:
                System.out.print("Enter valid choice (1-4): ");
            }
        }
    }
}
