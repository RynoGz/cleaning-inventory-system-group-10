package za.belgiumcampus.cleaninginventory.dao;
import java.sql.Connection;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterialDAO {
    DatabaseConnection db = new DatabaseConnection();
    
    public void insertMaterial(String name, String description, int qty, int reorderLevel, Integer supplierId) {
        String sql = "INSERT INTO materials (name, description, quantity_available, reorder_level, supplier_id) VALUES (?, ?, ?, ?, ?)";
        try ( Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, qty);
            pstmt.setInt(4, reorderLevel);
            if (supplierId != null) {
                pstmt.setInt(5, supplierId);
            } else {
                pstmt.setNull(5, java.sql.Types.INTEGER);
            }
            pstmt.executeUpdate();
            System.out.println("Material inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Update available quantity of a specific material
    public boolean updateMaterialStock(int materialId, int newQuantity) throws SQLException{
        String sql = "UPDATE materials SET quantity_available = ? WHERE material_id = ?";
        
        try ( Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, materialId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the user was found and updated
        }
    }
}
