package za.belgiumcampus.cleaninginventory.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;
import za.belgiumcampus.cleaninginventory.model.Cleaner;

public class CleanerDAO {
   
    DatabaseConnection db = new DatabaseConnection();

    public boolean insertCleaner(Cleaner cleaner){
        String sql = """
                INSERT INTO cleaners (firstName, lastName, email, phone, department) Values (?, ?, ?, ?, ?) """;
        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, cleaner.getFirstName());
            pstmt.setString(2, cleaner.getLastName());
            pstmt.setString(3, cleaner.getEmail());
            pstmt.setString(4, cleaner.getPhone());
            pstmt.setString(5, cleaner.getDepartment());


            return pstmt.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;

        }
    }
    
    public boolean updateCleaner(Cleaner cleaner) throws SQLException {
        String sql = "UPDATE cleaners SET first_name = ?, last_name = ?, email = ?, phone = ?, department = ? WHERE cleaner_id = ?";

        Connection con = db.getCon();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cleaner.getFirstName());
            pstmt.setString(2, cleaner.getLastName());
            pstmt.setString(3, cleaner.getEmail());
            pstmt.setString(4, cleaner.getPhone());
            pstmt.setString(5, cleaner.getDepartment());
            pstmt.setInt(6, cleaner.getCleanerId());

            return pstmt.executeUpdate() > 0;

        }
    }

    public boolean deleteCleaner(int cleanerId) throws SQLException{


        String sql = "DELETE FROM cleaners WHERE cleaner_id = ?";

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, cleanerId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public List<Cleaner> getAllCleaners() throws SQLException {

        List<Cleaner> cleaners = new ArrayList<>();

        String sql = """
            SELECT *
            FROM cleaners
            ORDER BY name
        """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cleaner cleaner = new Cleaner();
                cleaner.setCleanerId(rs.getInt("cleaner_id"));
                cleaner.setFirstName(rs.getString("first_name"));
                cleaner.setLastName(rs.getString("last_name"));
                cleaner.setPhone(rs.getString("phone"));
                cleaner.setEmail(rs.getString("email"));
                cleaner.setDepartment(rs.getString("department"));
                cleaner.setIsActive(rs.getBoolean("is_active"));

                cleaners.add(cleaner);
            }
        }

        return cleaners;
    }

    public List<Cleaner> searchCleaners(String searchText) throws SQLException {

        List<Cleaner> cleaners = new ArrayList<>();

        String sql = """
            SELECT *
            FROM cleaners
            WHERE LOWER(name) LIKE ?
            OR LOWER(phone) LIKE ?
            OR LOWER(department) Like ?
            ORDER BY name
        """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            String search = "%" + searchText.toLowerCase() + "%";

            pstmt.setString(1, search);
            pstmt.setString(2, search);
            pstmt.setString(2, search);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Cleaner cleaner = new Cleaner();
                cleaner.setCleanerId(rs.getInt("cleaner_id"));
                cleaner.setFirstName(rs.getString("firstName"));
                cleaner.setPhone(rs.getString("phone"));
                cleaner.setDepartment(rs.getString("department"));


                cleaners.add(cleaner);
            }
        }

        return cleaners;
    }
}
