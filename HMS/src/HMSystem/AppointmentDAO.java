package HMSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public static void addAppointment(Appointment appointment) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        String query = "INSERT INTO appointment (patient_id, doctor_id, appointment_date, reason_of_visit) VALUES (?,?,?,?)";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, appointment.getPatient_id());
            psmt.setInt(2, appointment.getDoctor_id());
            psmt.setDate(3, appointment.getAppointmentDate());
            psmt.setString(4, appointment.getReason());

            int rowsAffected = psmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment inserted successfully!");
            } else {
                System.out.println("Failed to insert Appointment.");
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Cannot add or update appointment. The patient ID or doctor ID does not exist.");
        }catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    public static List<Appointment> getAllAppointments() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return null;
        }
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointment";
        try (Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(query)) {
            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDate("appointment_date"),
                        rs.getString("reason_of_visit"));
                appointments.add(appointment);
            }
            return appointments;
        }
    }

    public static Appointment getAppointmentById(int appointmentId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return null;
        }
        String query = "SELECT * FROM appointment WHERE appointment_id = ?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, appointmentId);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDate("appointment_date"),
                        rs.getString("reason_of_visit"));
            } else {
                System.out.println("Appointment not found.");
                return null;
            }
        }
    }

    public static void updateAppointment(int id, Appointment appointment) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        String query = "UPDATE appointment SET patient_id = ?, doctor_id = ?, appointment_date = ?, reason_of_visit = ? WHERE appointment_id = ?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, appointment.getPatient_id());
            psmt.setInt(2, appointment.getDoctor_id());
            psmt.setDate(3, appointment.getAppointmentDate());
            psmt.setString(4, appointment.getReason());
            psmt.setInt(5, id);

            int rowsAffected = psmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment updated successfully!");
            } else {
                System.out.println("Failed to update Appointment.");
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Cannot add or update appointment. The patient ID or doctor ID does not exist.");
        }catch(SQLException e) {
        	e.printStackTrace();
        }
    }

    public static void deleteAppointment(int id) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        String query = "DELETE FROM appointment WHERE appointment_id = ?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, id);
            int rowsAffected = psmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment deleted successfully!");
            } else {
                System.out.println("Cannot delete Appointment.");
            }
        }
    }
}
