package HMSystem;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
	public static void menu(Scanner sc) throws SQLException {
		
		while(true) {
			System.out.println("Enter your choice!");
			System.out.println("Press 1: Manage Patients");
			System.out.println("Press 2: Manage Doctors");
			System.out.println("Press 3: Manage Appointments");
			System.out.println("Press 4: Manage Staffs");
			System.out.println("Press 5: Exit");
			
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				ManagePatients.menu(sc);
				break;
			case 2:
				Managedoctors.menu(sc);
				break;
			case 3:
				ManageAppointment.menu(sc);
				break;
			case 4:
				ManageStaff.menu(sc);
				break;
			case 5:
				System.out.println("Exiting from the system!");
                sc.close();
                return;
            default:
                System.out.println("Enter valid choice (1-3): ");
                System.out.println("================================");
                System.out.println("");
			}
		}
	}
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		menu(sc);
	}
}
