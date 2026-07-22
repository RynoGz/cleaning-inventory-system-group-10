package za.belgiumcampus.cleaninginventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;
import za.belgiumcampus.cleaninginventory.model.Issue;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssieDAO {
    DatabaseConnection db = new DatabaseConnection();
    
    // Insert an Issuance (Returns the auto-generated issuance_id)
    public int insertIssuance(Issue issuance) {

        String sql = """
            INSERT INTO issuance
            (material_id, cleaner_id, quantity_issued)
            VALUES (?, ?, ?)
        """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, issuance.getMaterialId());
            pstmt.setInt(2, issuance.getCleanerId());
            pstmt.setInt(3, issuance.getQuantityIssued());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()){
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

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

    public boolean updateIssuance(Issue issuance) throws SQLException {

        String sql = """
            UPDATE issuance
            SET material_id = ?, cleaner_id = ?, quantity_issued = ?
            WHERE issuance_id = ?
        """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, issuance.getMaterialId());
            pstmt.setInt(2, issuance.getCleanerId());
            pstmt.setInt(3, issuance.getQuantityIssued());
            pstmt.setInt(4, issuance.getIssuanceId());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteIssuance(int issuanceId) throws SQLException {

        String sql = "DELETE FROM issuance WHERE issuance_id = ?";

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, issuanceId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public List<Issue> getAllIssuances() throws SQLException {

        List<Issue> list = new ArrayList<>();

        String sql = """
            SELECT *
            FROM issuance
            ORDER BY issuance_id DESC
        """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Issue issuance = new Issue();

                issuance.setIssuanceId(rs.getInt("issuance_id"));
                issuance.setMaterialId(rs.getInt("material_id"));
                issuance.setCleanerId(rs.getInt("cleaner_id"));
                issuance.setQuantityIssued(rs.getInt("quantity_issued"));
                issuance.setCreatedAt(rs.getTimestamp("created_at"));

                list.add(issuance);
            }
        }

        return list;
    }
    public List<Issue> searchIssuances(String searchText) throws SQLException {

        List<Issue> list = new ArrayList<>();

        String sql = """
            SELECT *
            FROM issuance
            WHERE CAST(material_id AS VARCHAR) LIKE ?
            OR CAST(cleaner_id AS VARCHAR) LIKE ?
            ORDER BY issuance_id DESC
        """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            String search = "%" + searchText.toLowerCase() + "%";

            pstmt.setString(1, search);
            pstmt.setString(2, search);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Issue issuance = new Issue();

                issuance.setIssuanceId(rs.getInt("issuance_id"));
                issuance.setMaterialId(rs.getInt("material_id"));
                issuance.setCleanerId(rs.getInt("cleaner_id"));
                issuance.setQuantityIssued(rs.getInt("quantity_issued"));
                issuance.setCreatedAt(rs.getTimestamp("created_at"));

                list.add(issuance);
            }
        }

        return list;
    }
}
