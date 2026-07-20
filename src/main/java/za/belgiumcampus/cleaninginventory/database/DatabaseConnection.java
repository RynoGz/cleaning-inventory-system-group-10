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
    
    public DatabaseConnection() {
    try {
        connect();
    } catch (ClassNotFoundException e) {
        throw new RuntimeException("Failed to connect to the database.", e);
    }
}
    
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
    
    public Connection getCon() throws SQLException {

    try {
        Class.forName(DRIVER);
    } catch (ClassNotFoundException e) {
        throw new SQLException("Failed to load Derby driver.", e);
    }

    return DriverManager.getConnection(JDBC_URL);
}
}
