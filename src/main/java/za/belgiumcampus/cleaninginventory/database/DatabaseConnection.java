package za.belgiumcampus.cleaninginventory.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseConnection {
    
    //For Derby databse:
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:dbCleaningInventory;create=true";
            
    Connection con;
    
    public void connect() throws ClassNotFoundException{
        try{
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(JDBC_URL);
            
            if (this.con != null){
                System.out.println("Database is connected");
            }
            
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void createTable() {
        try {
            // Users Table
            if(!tableExists(con, "users")) {
                String query = """
                    CREATE TABLE users (
                        user_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
                        username VARCHAR(50) UNIQUE NOT NULL,
                        email VARCHAR(100) UNIQUE NOT NULL,
                        password_hash VARCHAR(255) NOT NULL,
                        role VARCHAR(20) DEFAULT 'Staff' NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT chk_role CHECK (role IN ('Admin', 'Staff'))
                    )
                    """;
                this.con.createStatement().execute(query);
            }

            // Suppliers Table
            if(!tableExists(con, "suppliers")) {
                String query = """
                    CREATE TABLE suppliers (
                        supplier_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        contact_person VARCHAR(100),
                        phone VARCHAR(20) NOT NULL,
                        email VARCHAR(100) UNIQUE,
                        address VARCHAR(32672), -- Derby equivalent for TEXT
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )
                    """;
                this.con.createStatement().execute(query);
            }

            // Cleaners Table
            if(!tableExists(con, "cleaners")) {
                String query = """
                    CREATE TABLE cleaners (
                        cleaner_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
                        first_name VARCHAR(50) NOT NULL,
                        last_name VARCHAR(50) NOT NULL,
                        email VARCHAR(100) UNIQUE,
                        phone VARCHAR(20),
                        department VARCHAR(50),
                        is_active BOOLEAN DEFAULT TRUE,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )
                    """;
                this.con.createStatement().execute(query);
            }

            // Materials Table
            if(!tableExists(con, "materials")) {
                String query = """
                    CREATE TABLE materials (
                        material_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
                        name VARCHAR(100) NOT NULL UNIQUE,
                        description VARCHAR(32672),
                        quantity_available INT DEFAULT 0 NOT NULL,
                        reorder_level INT DEFAULT 10 NOT NULL,
                        supplier_id INT REFERENCES suppliers(supplier_id) ON DELETE SET NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT chk_positive_stock CHECK (quantity_available >= 0),
                        CONSTRAINT chk_positive_reorder CHECK (reorder_level >= 0)
                    )
                    """;
                this.con.createStatement().execute(query);
            }

            // Issuances Table
            if(!tableExists(con, "issuances")) {
                String query = """
                    CREATE TABLE issuances (
                        issuance_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
                        cleaner_id INT NOT NULL REFERENCES cleaners(cleaner_id),
                        issued_by_user_id INT NOT NULL REFERENCES users(user_id),
                        issuance_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )
                    """;
                this.con.createStatement().execute(query);
            }

            // Issuance Details Table
            if(!tableExists(con, "issuance_details")) {
                String query = """
                    CREATE TABLE issuance_details (
                        detail_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
                        issuance_id INT NOT NULL REFERENCES issuances(issuance_id) ON DELETE CASCADE,
                        material_id INT NOT NULL REFERENCES materials(material_id),
                        quantity_issued INT NOT NULL,
                        CONSTRAINT chk_positive_issued_qty CHECK (quantity_issued > 0)
                    )
                    """;
                this.con.createStatement().execute(query);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static boolean tableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        
        try (ResultSet rs = meta.getTables(null,null, tableName.toUpperCase(), new String[]{"TABLE"})){
            return rs.next();
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
 
    // Insert a new user
    public void insertUser(String username, String email, String passwordHash, String role) {
        String sql = "INSERT INTO users (username, email, password_hash, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.con.prepareStatement(sql)) {
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

    // Insert a Supplier
    public void insertSupplier(String name, String contactPerson, String phone, String email, String address) {
        String sql = "INSERT INTO suppliers (name, contact_person, phone, email, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.con.prepareStatement(sql)) {
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
    
    // Insert a new Material
    public void insertMaterial(String name, String description, int qty, int reorderLevel, Integer supplierId) {
        String sql = "INSERT INTO materials (name, description, quantity_available, reorder_level, supplier_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.con.prepareStatement(sql)) {
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
    
    // Insert a Cleaner
    public void insertCleaner(String firstName, String lastName, String email, String phone, String department, boolean isActive) {
        String sql = "INSERT INTO cleaners (first_name, last_name, email, phone, department, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.con.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, department);
            pstmt.setBoolean(6, isActive);
            pstmt.executeUpdate();
            System.out.println("Cleaner '" + firstName + " " + lastName + "' registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Insert an Issuance (Returns the auto-generated issuance_id)
    public int insertIssuance(int cleanerId, int issuedByUserId) {
        String sql = "INSERT INTO issuances (cleaner_id, issued_by_user_id) VALUES (?, ?)";
        int generatedId = -1;

        // We pass Statement.RETURN_GENERATED_KEYS to retrieve the ID Derby just created
        try (PreparedStatement pstmt = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, cleanerId);
            pstmt.setInt(2, issuedByUserId);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
            System.out.println("Issuance recorded with ID: " + generatedId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId; // Use this ID to link details
    }

    // Insert Issuance Details
    public void insertIssuanceDetail(int issuanceId, int materialId, int quantityIssued) {
        String sql = "INSERT INTO issuance_details (issuance_id, material_id, quantity_issued) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = this.con.prepareStatement(sql)) {
            pstmt.setInt(1, issuanceId);
            pstmt.setInt(2, materialId);
            pstmt.setInt(3, quantityIssued);
            pstmt.executeUpdate();
            System.out.println("Detail linked: Material ID " + materialId + " (Qty: " + quantityIssued + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Update available quantity of a specific material
    public void updateMaterialStock(int materialId, int newQuantity) {
        String sql = "UPDATE materials SET quantity_available = ? WHERE material_id = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, materialId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stock updated successfully!");
            } else {
                System.out.println("Material ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return con;
    }
}
