package za.belgiumcampus.cleaninginventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;

public class CleanerDAO {
    DatabaseConnection db = new DatabaseConnection();
    
    public void insertCleaner(String firstName, String lastName, String email, String phone, String department, boolean isActive) {
        String sql = "INSERT INTO cleaners (first_name, last_name, email, phone, department, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        try ( Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, department);
            pstmt.setBoolean(6, isActive);
            pstmt.executeUpdate();
            System.out.println("Cleaner '" + firstName + " " + lastName + "' registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean updateCleaner(int cleanerId, String firstName, String lastName, String email, String phone, String department, boolean isActive) throws SQLException {
        String sql = "UPDATE cleaners SET first_name = ?, last_name = ?, email = ?, phone = ?, department = ?, is_active = ? WHERE cleaner_id = ?";

        Connection con = db.getCon();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, department);
            pstmt.setBoolean(6, isActive);
            pstmt.setInt(7, cleanerId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
