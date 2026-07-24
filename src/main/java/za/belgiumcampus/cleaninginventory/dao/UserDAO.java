package za.belgiumcampus.cleaninginventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;
import java.sql.ResultSet;
import za.belgiumcampus.cleaninginventory.model.User;
import java.util.List;
import java.util.ArrayList;

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
    
    public User login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";

        try (
            Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean usernameExists(String username) {

        String sql = "SELECT user_id FROM users WHERE username = ?";

        try (
            Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {

            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean emailExists(String email) {

        String sql = "SELECT user_id FROM users WHERE email = ?";

        try (
            Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users ORDER BY username";

        try (
            Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }  
    
    public boolean deleteUser(int userId) {

        String sql = "DELETE FROM users WHERE user_id = ?";

        try (
            Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {

            pstmt.setInt(1, userId);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }   
    
    public List<User> searchUsers(String searchText) {

        List<User> users = new ArrayList<>();

        String sql =
            "SELECT * FROM users " +
            "WHERE LOWER(username) LIKE ? OR LOWER(email) LIKE ? " +
            "ORDER BY username";

        try (
            Connection con = db.getCon();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {

            String search = "%" + searchText.toLowerCase() + "%";

            pstmt.setString(1, search);
            pstmt.setString(2, search);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
