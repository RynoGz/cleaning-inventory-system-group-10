package za.belgiumcampus.cleaninginventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;
import za.belgiumcampus.cleaninginventory.model.Supplier;

public class SupplierDAO {
    DatabaseConnection db = new DatabaseConnection();

    public boolean insertSupplier(Supplier supplier) {

        String sql = """
            INSERT INTO suppliers
            (name, contact_person, phone, email,address)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getContactPerson());
            pstmt.setString(3, supplier.getPhone());
            pstmt.setString(4, supplier.getEmail());
            pstmt.setString(5, supplier.getAddress());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSupplier(Supplier supplier) throws SQLException {

        String sql = """
            UPDATE suppliers
            SET name = ?,
                contact_person = ?,
                phone = ?,
                email = ?,
                address = ?
            WHERE supplier_id = ?
            """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getContactPerson());
            pstmt.setString(3, supplier.getPhone());
            pstmt.setString(4, supplier.getEmail());
            pstmt.setString(5, supplier.getAddress());
            pstmt.setInt(6, supplier.getSupplierId());

            return pstmt.executeUpdate() > 0;
        }
    }

    public List<Supplier> getAllSuppliers() throws SQLException {

        List<Supplier> suppliers = new ArrayList<>();

        String sql = """
            SELECT *
            FROM suppliers
            ORDER BY name
            """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Supplier supplier = new Supplier();

                supplier.setSupplierId(rs.getInt("supplier_id"));
                supplier.setName(rs.getString("name"));
                supplier.setContactPerson(rs.getString("contact_person"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setEmail(rs.getString("email"));
                supplier.setAddress(rs.getString("address"));
                supplier.setCreatedAt(rs.getTimestamp("created_at"));

                suppliers.add(supplier);
            }
        }

        return suppliers;
    }

    public boolean deleteSupplier(int supplierId) throws SQLException {

        String sql = "DELETE FROM suppliers WHERE supplier_id = ?";

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, supplierId);

            return pstmt.executeUpdate() > 0;
        }
    }

    public List<Supplier> searchSuppliers(String searchText) throws SQLException {

        List<Supplier> suppliers = new ArrayList<>();

        String sql = """
            SELECT *
            FROM suppliers
            WHERE LOWER(name) LIKE ?
               OR LOWER(contact_person) LIKE ?
               OR LOWER(email) LIKE ?
                OR LOWER(address) LIKE ?
            ORDER BY name
            """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            String search = "%" + searchText.toLowerCase() + "%";

            pstmt.setString(1, search);
            pstmt.setString(2, search);
            pstmt.setString(3, search);
            pstmt.setString(4, search);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Supplier supplier = new Supplier();

                supplier.setSupplierId(rs.getInt("supplier_id"));
                supplier.setName(rs.getString("name"));
                supplier.setContactPerson(rs.getString("contact_person"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setEmail(rs.getString("email"));
                supplier.setAddress(rs.getString("address"));
                supplier.setCreatedAt(rs.getTimestamp("created_at"));

                suppliers.add(supplier);
            }
        }

        return suppliers;
    }


}
