package HMSystem;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ManageAppointment {
    public static void menu(Scanner sc) throws SQLException {
        while(true) {
            System.out.println("Enter your Choice:");
            System.out.println("Press 1: Insert New Appointment");
            System.out.println("Press 2: Update Appointment");
            System.out.println("Press 3: Delete Appointment");
            System.out.println("Press 4: View Appointments");
            System.out.println("Press 5: Exit");
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    AppointmentDAO.addAppointment(DetailEntry(sc));
                    break;
                case 2:
                    System.out.println("Enter Appointment id to update: ");
                    int id = sc.nextInt();
                    AppointmentDAO.updateAppointment(id, DetailEntry(sc));
                    break;
                case 3:
                    System.out.println("Enter Appointment id to Delete: ");
                    int id2 = sc.nextInt();
                    AppointmentDAO.deleteAppointment(id2);
                    break;
                case 4:
                    System.out.println("Enter Appointment id to View: ");
                    int id3 = sc.nextInt();
                    Appointment appointment = AppointmentDAO.getAppointmentById(id3);
                    if (appointment != null) {
                        System.out.println("Appointment ID: " + appointment.getAppointment_id() +
                                           "\nPatient ID: " + appointment.getPatient_id() +
                                           "\nDoctor ID: " + appointment.getDoctor_id() +
                                           "\nAppointment Date: " + appointment.getAppointmentDate() +
                                           "\nReason of Visit: " + appointment.getReason());
                        System.out.println("===========================================================");
                    }
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

    public static Appointment DetailEntry(Scanner sc) {
        System.out.println("Enter your Updated details:");
        sc.nextLine(); // Consume newline
        System.out.println("Enter Patient id:");
        int patient_id = sc.nextInt();
        System.out.println("Enter Doctor id:");
        int doctor_id = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.println("Enter Date (yyyy-MM-dd):");
        String dateappointment = sc.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date appointmentDate;
        try {
            java.util.Date utilDate = dateFormat.parse(dateappointment);
            appointmentDate = new Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            return null;
        }
        System.out.println("Enter Reason of visit:");
        String reason = sc.nextLine();
        return new Appointment(patient_id, doctor_id, appointmentDate, reason);
    }

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        menu(sc);
    }
}
