package za.belgiumcampus.cleaninginventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;

public class UserDAO {
    DatabaseConnection db = new DatabaseConnection();

    public void insertUser(String username, String email, String passwordHash, String role){
        String sql = "INSERT INTO users (username, email, password_hash, role) VALUES (?, ?, ?, ?)";
        
        try (Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, passwordHash);
            pstmt.setString(4, role);
            pstmt.executeUpdate();
            System.out.println("User inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean updateUser(int userId, String username, String email, String passwordHash, String role) throws SQLException{
        String sql = " UPDATE users SET username = ?, email = ?, password_hash = ?, role = ? WHERE user_id = ? ";

        try ( Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, passwordHash);
            pstmt.setString(4, role);
            pstmt.setInt(5, userId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the user was found and updated
        }
    }
}
