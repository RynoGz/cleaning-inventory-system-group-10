package za.belgiumcampus.cleaninginventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;

public class IssieDAO {
    DatabaseConnection db = new DatabaseConnection();
    
    // Insert an Issuance (Returns the auto-generated issuance_id)
    public int insertIssuance(int cleanerId, int issuedByUserId) {
        String sql = "INSERT INTO issuances (cleaner_id, issued_by_user_id) VALUES (?, ?)";
        int generatedId = -1;

        // We pass Statement.RETURN_GENERATED_KEYS to retrieve the ID Derby just created
        try ( Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, cleanerId);
            pstmt.setInt(2, issuedByUserId);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
            System.out.println("Issuance recorded with ID: " + generatedId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId; // Use this ID to link details
    }

    // Insert Issuance Details
    public void insertIssuanceDetail(int issuanceId, int materialId, int quantityIssued) {
        String sql = "INSERT INTO issuance_details (issuance_id, material_id, quantity_issued) VALUES (?, ?, ?)";
        try ( Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, issuanceId);
            pstmt.setInt(2, materialId);
            pstmt.setInt(3, quantityIssued);
            pstmt.executeUpdate();
            System.out.println("Detail linked: Material ID " + materialId + " (Qty: " + quantityIssued + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
