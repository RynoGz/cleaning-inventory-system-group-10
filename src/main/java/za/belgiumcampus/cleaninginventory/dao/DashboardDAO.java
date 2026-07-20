package za.belgiumcampus.cleaninginventory.dao;

import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;
import za.belgiumcampus.cleaninginventory.model.Issue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO {

    private final DatabaseConnection db = new DatabaseConnection();

    public int getTotalMaterials() {
        String sql = "SELECT COUNT(*) FROM materials";
        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalCleaners() {
        String sql = "SELECT COUNT(*) FROM cleaners";
        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLowStockCount() {
        String sql = "SELECT COUNT(*) FROM materials WHERE quantity_available <= reorder_level";
        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Issue> getRecentIssues() {
        // TODO: Implement once IssieDAO and Issue model are completed
        return new ArrayList<>();
    }
}

