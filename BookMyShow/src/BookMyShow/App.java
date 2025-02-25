package BookMyShow;
import java.sql.SQLException;
import java.util.*;
public class App {
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("");
			System.out.println("Enter Choice: ");
			System.out.println("Press 1: Create New User");
			System.out.println("Press 2: Create New Admin");
			System.out.println("Press 3: Log in - Existing User/Admin");
			System.out.println("Press 4: Exit");
			System.out.println("================================");
            System.out.println("");
			int n = sc.nextInt();
			switch(n) {
				case 1:
					AdminControl.insertUser(sc);
					break;
				case 2:
					AdminControl.insertAdmin(sc);
					break;
				case 3: 
					LogingIn.Login(sc);
					break;
				case 4:
					System.out.println("Exiting from the system!");
	                sc.close();
	                return;
	            default:
	                System.out.println("Enter valid choice (1-4): ");
	                System.out.println("================================");
	                System.out.println("");
	        }
		}
		
	}
			
				
}
	

