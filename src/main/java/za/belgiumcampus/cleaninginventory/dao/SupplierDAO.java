package za.belgiumcampus.cleaninginventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;

public class SupplierDAO {
    DatabaseConnection db = new DatabaseConnection();
    
    public void insertSupplier(String name, String contactPerson, String phone, String email, String address) {
        String sql = "INSERT INTO suppliers (name, contact_person, phone, email, address) VALUES (?, ?, ?, ?, ?)";
        try ( Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, contactPerson);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            pstmt.executeUpdate();
            System.out.println("Supplier '" + name + "' added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean updateSupplier(int supplierId, String name, String contactPerson, String phone, String email, String address) throws SQLException {
        String sql = "UPDATE suppliers SET name = ?, contact_person = ?, phone = ?, email = ?, address = ? WHERE supplier_id = ? ";

        Connection con = db.getCon();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, contactPerson);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            pstmt.setInt(6, supplierId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
