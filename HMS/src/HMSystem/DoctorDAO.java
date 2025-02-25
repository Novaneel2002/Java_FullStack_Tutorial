package HMSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
	public static void addDoctor(Doctor doctor) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }
        String query = "Insert into doctor (name,speciality, contact) values (?,?,?)";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setString(1, doctor.getName());
            psmt.setString(2, doctor.getSpeciality());
            psmt.setString(3, doctor.getContact());
            

            psmt.executeUpdate();
        }
    }
	

	public static List<Doctor> getAllDoctors() throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return null;
	    }
        List<Doctor> doctors = new ArrayList<>();
        String query = "Select * from doctor";
        try (Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(query)) {
            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getString("name"),
                        rs.getString("speciality"),
                        rs.getString("contact"));
                doctors.add(doctor);
            }
            return doctors;
        }
    }

    public static void updateDoctor(int id, Doctor doctor) throws SQLException {
    	Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }
        String query = "UPDATE doctor SET name = ?, speciality = ?, contact = ? WHERE doctor_id = ?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setString(1, doctor.getName());
            psmt.setString(2, doctor.getSpeciality());
            psmt.setString(3, doctor.getContact());
            psmt.setInt(4, id);
            int rowsAffected = psmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Doctor updated successfully!");
            } else {
                System.out.println("Failed to update Doctor.");
            }
        }
    }

    public static void deleteDoctor(int id) throws SQLException {
    	Connection conn = DatabaseConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Database Connection Failed");
	        return;
	    }
        String query = "DELETE FROM doctor WHERE doctor_id = ?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, id);
            int rowsAffected = psmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Doctor deleted successfully!");
            } else {
                System.out.println("Cannot delete Doctor.");
            }
        }
    }

}
