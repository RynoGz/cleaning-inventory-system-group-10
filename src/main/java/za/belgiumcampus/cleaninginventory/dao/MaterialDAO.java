package za.belgiumcampus.cleaninginventory.dao;
import java.sql.Connection;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import za.belgiumcampus.cleaninginventory.model.Material;

public class MaterialDAO {
        private DatabaseConnection databaseConnection;
        private Connection connection;

    public MaterialDAO(){
        databaseConnection = new DatabaseConnection();
        try {
            databaseConnection.connect();
            connection = databaseConnection.getConnection();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
