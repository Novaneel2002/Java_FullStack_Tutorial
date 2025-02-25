package HMSystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ManagePatients {
	public static void menu(Scanner sc) throws SQLException {
		while(true) {
		System.out.println("Enter your Choice:");
		System.out.println("Press 1: Insert New Patient detail");
		System.out.println("Press 2: Update Patient detail");
		System.out.println("Press 3: Delete Patient detail");
		System.out.println("Press 4: View All Patients");
		int choice = sc.nextInt();
		
			switch(choice) {
			case 1:
				PatientDAO.addPatient(DetailEntry(sc));
				
				break;
			case 2:
				System.out.println("Enter patient id to update: ");
				int id = sc.nextInt();
				sc.nextLine();
				PatientDAO.updatePatient(id, DetailEntry(sc));
				break;
			case 3:
				System.out.println("Enter patient id to update: ");
				int id2 = sc.nextInt();
				PatientDAO.deletePatient(id2);
				break;
			case 4:
				List<Patient> patients = PatientDAO.getAllPatients();
	            System.out.println("Patients in the database:");
	            for (Patient patient : patients) {
	                System.out.println("First Name: " + patient.getFname() +
	                                   "\nLast Name: " + patient.getLname() +
	                                   "\nAge: " + patient.getAge() +
	                                   "\nGender: " + patient.getGender() +
	                                   "\nContact Number: " + patient.getContactNumber());
	                System.out.println("===========================================================");
	            }
	            System.out.println("===========================================================");
	            break;
			case 5:
				System.out.println("Exiting from the system!");
                sc.close();
                return;
            default:
                System.out.println("Enter valid choice (1-5): ");
                System.out.println("================================");
                System.out.println("");
			}
			
		}
		
	}
		
	public static Patient DetailEntry(Scanner sc) {
		System.out.println("Enter your Updated details:");
//		sc.nextLine();
		System.out.println("Enter first name:");
		String fname = sc.nextLine();
		System.out.println("Enter Last name:");
		String lname = sc.nextLine();
		System.out.println("Enter age:");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Gender:");
		String gender = sc.nextLine();
		System.out.println("Enter contact Number:");
		String contactnumber = sc.nextLine();
		Patient p = new Patient(fname,lname,age,gender,contactnumber);
		return p;
	}
		
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		menu(sc);
	}
	
}
