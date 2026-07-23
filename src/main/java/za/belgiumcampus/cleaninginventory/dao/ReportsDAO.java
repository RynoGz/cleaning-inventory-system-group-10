package za.belgiumcampus.cleaninginventory.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;
import za.belgiumcampus.cleaninginventory.model.*;

public class ReportsDAO {
    
    DatabaseConnection db = new DatabaseConnection();

    // Inventory Report

    public List<InventoryReport> getInventoryReport() throws SQLException {

        List<InventoryReport> list = new ArrayList<>();

        String sql = """
            SELECT material_id, name, quantity_available
            FROM materials
            ORDER BY name
        """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                InventoryReport report = new InventoryReport();
                report.setMaterialId(rs.getInt("material_id"));
                report.setMaterialName(rs.getString("name"));
                report.setQuantityAvailable(rs.getInt("quantity_available"));
                list.add(report);
            }
        }

        return list;
    }

    // Low Stock Report

    public List<LowStockReport> getLowStockReport(int threshold) throws SQLException {

        List<LowStockReport> list = new ArrayList<>();

        String sql = """
            SELECT material_id, name, quantity_available
            FROM materials
            WHERE quantity_available <= ?
            ORDER BY quantity_available ASC
        """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, threshold);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LowStockReport report = new LowStockReport();
                report.setMaterialId(rs.getInt("material_Id"));
                report.setMaterialName(rs.getString("name"));
                report.setQuantityAvailable(rs.getInt("quantity_available"));
                list.add(report);
            }
        }

        return list;
    }

    // Issuance History Report
    public List<IssuanceHistoryReport> getIssuanceHistory(int cleanerId) throws SQLException {

        List<IssuanceHistoryReport> list = new ArrayList<>();

        String sql = """
            SELECT i.issuance_id, i.material_id, m.name AS material_name,
                   i.quantity_issued, i.created_at
            FROM issuance i
            JOIN materials m ON i.material_id = m.material_id
            WHERE i.cleaner_id = ?
            ORDER BY i.created_at DESC
        """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, cleanerId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                IssuanceHistoryReport report = new IssuanceHistoryReport();
                report.setIssuanceId(rs.getInt("issuance_id"));
                report.setMaterialId(rs.getInt("material_Id"));
                report.setMaterialName(rs.getString("material_name"));
                report.setQuantityIssued(rs.getInt("quantity_issued"));
                report.setIssuedAt(rs.getTimestamp("created_at"));
                list.add(report);
            }
        }

        return list;
    }


    // Material Usage Report

    public List<MaterialUsageReport> getMaterialUsageReport(int materialId) throws SQLException {

        List<MaterialUsageReport> list = new ArrayList<>();

        String sql = """
            SELECT i.issuance_id, c.name AS cleaner_name,
                   i.quantity_issued, i.created_at
            FROM issuance i
            JOIN cleaners c ON i.cleaner_id = c.cleaner_id
            WHERE i.material_id = ?
            ORDER BY i.created_at DESC
        """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, materialId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MaterialUsageReport report = new MaterialUsageReport();
                report.setIssuanceId(rs.getInt("issuance_id"));
                report.setQuantityIssued(rs.getInt("quantity_issued"));
                report.setIssuedAt(rs.getTimestamp("created_at"));
                list.add(report);
            }
        }

        return list;
    }
    
}
