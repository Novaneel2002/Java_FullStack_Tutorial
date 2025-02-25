package HMSystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ManageStaff {
    public static void menu(Scanner sc) throws SQLException {
        while(true) {
            System.out.println("Enter your Choice:");
            System.out.println("Press 1: Insert New Staff detail");
            System.out.println("Press 2: Update Staff detail");
            System.out.println("Press 3: Delete Staff detail");
            System.out.println("Press 4: View All Staff");
            System.out.println("Press 5: Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                    StaffDAO.addStaff(DetailEntry(sc));
                    break;
                case 2:
                    System.out.println("Enter staff id to update: ");
                    String id = sc.next();
                    sc.nextLine();
                    StaffDAO.updateStaff(id, DetailEntry(sc));
                    break;
                case 3:
                    System.out.println("Enter staff id to delete: ");
                    String id2 = sc.next();
                    StaffDAO.deleteStaff(id2);
                    break;
                case 4:
                    List<Staff> staffList = StaffDAO.getAllStaff();
                    System.out.println("Staff in the database:");
                    for (Staff staff : staffList) {
                        System.out.println("Staff ID: " + staff.getStaff_id() +
                                           "\nName: " + staff.getName() +
                                           "\nRole: " + staff.getRole() +
                                           "\nContact: " + staff.getContact());
                        System.out.println("===========================================================");
                    }
                    System.out.println("===========================================================");
                    break;
                case 5:
                    System.out.println("Exiting from the system!");
                    sc.close();
                    return;
                default:
                    System.out.println("Enter a valid choice (1-5): ");
                    System.out.println("================================");
                    System.out.println("");
            }
        }
    }

    public static Staff DetailEntry(Scanner sc) {
        System.out.println("Enter your Updated details:");
        System.out.println("Enter Name:");
        String name = sc.nextLine();
        System.out.println("Enter Role:");
        String role = sc.nextLine();
        System.out.println("Enter Contact:");
        String contact = sc.nextLine();
        return new Staff(name, role, contact);
    }

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        menu(sc);
    }
}
