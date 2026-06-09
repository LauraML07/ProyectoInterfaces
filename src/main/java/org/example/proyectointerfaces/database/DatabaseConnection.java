package org.example.proyectointerfaces.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;
    private static final String URL = "jdbc:sqlite:db/database_beta.db";

    private DatabaseConnection() {}

    public static Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("Conexión unica a SQLite exitosa");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos" + e.getMessage());
        }
        return connection;
    }
}
