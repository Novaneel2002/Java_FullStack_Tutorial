package HMSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {
    public static void addStaff(Staff staff) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        String query = "INSERT INTO staff (name, role, contact_number) VALUES (?,?,?)";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, staff.getName());
            psmt.setString(2, staff.getRole());
            psmt.setString(3, staff.getContact());

            int rowsAffected = psmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Staff inserted successfully!");
            } else {
                System.out.println("Failed to insert staff.");
            }
        }
    }

    public static List<Staff> getAllStaff() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return null;
        }
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff";
        try (Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(query)) {
            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getString("staff_id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("contact_number"));
                staffList.add(staff);
            }
            return staffList;
        }
    }

    public static Staff getStaffById(String staffId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return null;
        }
        String query = "SELECT * FROM staff WHERE staff_id = ?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, staffId);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return new Staff(
                        rs.getString("staff_id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("contact_number"));
            } else {
                System.out.println("Staff not found.");
                return null;
            }
        }
    }

    public static void updateStaff(String staffId, Staff staff) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        String query = "UPDATE staff SET name = ?, role = ?, contact_number = ? WHERE staff_id = ?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, staff.getName());
            psmt.setString(2, staff.getRole());
            psmt.setString(3, staff.getContact());
            psmt.setString(4, staffId);

            int rowsAffected = psmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Staff updated successfully!");
            } else {
                System.out.println("Failed to update staff.");
            }
        }
    }

    public static void deleteStaff(String staffId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        String query = "DELETE FROM staff WHERE staff_id = ?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, staffId);

            int rowsAffected = psmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Staff deleted successfully!");
            } else {
                System.out.println("Cannot delete staff.");
            }
        }
    }

    public static void main(String[] args) {
        // Your main method implementation
    }
}
