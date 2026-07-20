package za.belgiumcampus.cleaninginventory.dao;

import java.sql.Connection;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;
import za.belgiumcampus.cleaninginventory.model.Material;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MaterialDAO {
    DatabaseConnection db = new DatabaseConnection();

    public boolean insertMaterial(Material material) {

        String sql = """
            INSERT INTO materials
            (name, description, quantity_available, reorder_level, supplier_id)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, material.getName());
            pstmt.setString(2, material.getDescription());
            pstmt.setInt(3, material.getQuantityAvailable());
            pstmt.setInt(4, material.getReorderLevel());

            if (material.getSupplierId() != null) {
                pstmt.setInt(5, material.getSupplierId());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMaterial(Material material) throws SQLException {

        String sql = """
            UPDATE materials
            SET
                name = ?,
                description = ?,
                quantity_available = ?,
                reorder_level = ?,
                supplier_id = ?
            WHERE material_id = ?
            """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, material.getName());
            pstmt.setString(2, material.getDescription());
            pstmt.setInt(3, material.getQuantityAvailable());
            pstmt.setInt(4, material.getReorderLevel());

            if (material.getSupplierId() != null) {
                pstmt.setInt(5, material.getSupplierId());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            pstmt.setInt(6, material.getMaterialId());

            return pstmt.executeUpdate() > 0;
        }
    }

    public List<Material> getAllMaterials() throws SQLException {

        List<Material> materials = new ArrayList<>();

        String sql = """
            SELECT *
            FROM materials
            ORDER BY name
            """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Material material = new Material();

                material.setMaterialId(rs.getInt("material_id"));
                material.setName(rs.getString("name"));
                material.setDescription(rs.getString("description"));
                material.setQuantityAvailable(rs.getInt("quantity_available"));
                material.setReorderLevel(rs.getInt("reorder_level"));

                int supplierId = rs.getInt("supplier_id");

                if (rs.wasNull()) {
                    material.setSupplierId(null);
                } else {
                    material.setSupplierId(supplierId);
                }

                materials.add(material);
            }
        }

        return materials;
    }

    public boolean deleteMaterial(int materialId) throws SQLException {

        String sql = "DELETE FROM materials WHERE material_id = ?";

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, materialId);

            return pstmt.executeUpdate() > 0;
        }
    }

    public List<Material> searchMaterials(String searchText) throws SQLException {

        List<Material> materials = new ArrayList<>();

        String sql = """
            SELECT *
            FROM materials
            WHERE LOWER(name) LIKE ?
               OR LOWER(description) LIKE ?
            ORDER BY name
            """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            String search = "%" + searchText.toLowerCase() + "%";

            pstmt.setString(1, search);
            pstmt.setString(2, search);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Material material = new Material();

                material.setMaterialId(rs.getInt("material_id"));
                material.setName(rs.getString("name"));
                material.setDescription(rs.getString("description"));
                material.setQuantityAvailable(rs.getInt("quantity_available"));
                material.setReorderLevel(rs.getInt("reorder_level"));

                int supplierId = rs.getInt("supplier_id");

                if (rs.wasNull()) {
                    material.setSupplierId(null);
                } else {
                    material.setSupplierId(supplierId);
                }

                material.setCreatedAt(rs.getTimestamp("created_at"));

                materials.add(material);
            }
        }

        return materials;
    }

    public List<Material> getLowStockMaterials() throws SQLException {

        List<Material> materials = new ArrayList<>();

        String sql = """
            SELECT *
            FROM materials
            WHERE quantity_available <= reorder_level
            ORDER BY quantity_available ASC
            """;

        try (Connection con = db.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Material material = new Material();

                material.setMaterialId(rs.getInt("material_id"));
                material.setName(rs.getString("name"));
                material.setDescription(rs.getString("description"));
                material.setQuantityAvailable(rs.getInt("quantity_available"));
                material.setReorderLevel(rs.getInt("reorder_level"));

                int supplierId = rs.getInt("supplier_id");

                if (rs.wasNull()) {
                    material.setSupplierId(null);
                } else {
                    material.setSupplierId(supplierId);
                }

                material.setCreatedAt(rs.getTimestamp("created_at"));

                materials.add(material);
            }
        }

        return materials;
    }
}
