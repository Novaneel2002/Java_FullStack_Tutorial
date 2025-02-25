package HMSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PatientDAO {
    public static void addPatient(Patient patient) throws SQLException {
    	Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }
        String query = "Insert into Patient (f_name, l_name, age, gender, contact_number) values (?,?,?,?,?)";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setString(1, patient.getFname());
            psmt.setString(2, patient.getLname());
            psmt.setInt(3, patient.getAge());
            psmt.setString(4, patient.getGender());
            psmt.setString(5, patient.getContactNumber());

            psmt.executeUpdate();
        }
    }

    public static List<Patient> getAllPatients() throws SQLException {
    	Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return null;
	    }
        List<Patient> patients = new ArrayList<>();
        String query = "Select * from patient";
        try (Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(query)) {
            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getString("f_name"),
                        rs.getString("l_name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("contact_number"));
                patients.add(patient);
            }
            return patients;
        }
    }

    public static void updatePatient(int id, Patient patient) throws SQLException {
    	Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }
        String query = "UPDATE patient SET f_name = ?, l_name = ?, age = ?, gender = ?, contact_number = ? WHERE patient_id = ?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setString(1, patient.getFname());
            psmt.setString(2, patient.getLname());
            psmt.setInt(3, patient.getAge());
            psmt.setString(4, patient.getGender());
            psmt.setString(5, patient.getContactNumber());
            psmt.setInt(6, id);
            int rowsAffected = psmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Patient updated successfully!");
            } else {
                System.out.println("Failed to update patient.");
            }
        }
    }

    public static void deletePatient(int id) throws SQLException {
    	Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }
        String query = "DELETE FROM patient WHERE patient_id = ?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, id);
            int rowsAffected = psmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Patient deleted successfully!");
            } else {
                System.out.println("Cannot delete patient.");
            }
        }
    }

    public static void main(String[] args) {
        // Your main method implementation
    }
}
