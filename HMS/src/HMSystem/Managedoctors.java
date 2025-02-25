package HMSystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Managedoctors {
	public static void menu(Scanner sc) throws SQLException {
		while(true) {
		System.out.println("Enter your Choice:");
		System.out.println("Press 1: Insert New Doctor detail");
		System.out.println("Press 2: Update Doctor detail");
		System.out.println("Press 3: Delete Doctor detail");
		System.out.println("Press 4: View All Doctors");
		int choice = sc.nextInt();
		
			switch(choice) {
			case 1:
				DoctorDAO.addDoctor(DetailEntry(sc));
				
				break;
			case 2:
				System.out.println("Enter Doctor id to update: ");
				int id = sc.nextInt();
//				sc.nextLine();
				DoctorDAO.updateDoctor(id, DetailEntry(sc));
				break;
			case 3:
				System.out.println("Enter Doctor id to update: ");
				int id2 = sc.nextInt();
				DoctorDAO.deleteDoctor(id2);
				break;
			case 4:
				List<Doctor> doctors = DoctorDAO.getAllDoctors();
	            System.out.println("Doctors in the database:");
	            for (Doctor doctor : doctors) {
	                System.out.println("First Name: " + doctor.getName() +
	                                   "\nAge: " + doctor.getSpeciality() +
	                                   "\nContact Number: " + doctor.getContact());
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
		
	public static Doctor DetailEntry(Scanner sc) {
		System.out.println("Enter your Updated details:");
		sc.nextLine();
		System.out.println("Enter name:");
		String name = sc.nextLine();
		System.out.println("Enter Speciality:");
		String speciality = sc.nextLine();
		System.out.println("Enter contact Number:");
		String contact = sc.nextLine();
		Doctor p = new Doctor(name,speciality,contact);
		return p;
	}
		
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		menu(sc);
	}
	
}
