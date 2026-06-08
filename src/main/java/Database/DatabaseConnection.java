package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:db/database_beta.db";

    public static Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("La conexion a SQLite a sido exitosa");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos" + e.getMessage());
        }
        return connection;
    }
}
