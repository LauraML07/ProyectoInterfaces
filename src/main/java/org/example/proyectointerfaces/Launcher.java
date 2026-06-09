package org.example.proyectointerfaces;

import org.example.proyectointerfaces.database.DatabaseConnection;
import org.example.proyectointerfaces.database.Tablas;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Launcher {

    public static void main(String[] args) {
        resetearIds();
        try {
            System.out.println("Inicializando base de datos...");
            Tablas.crearTablas();
        } catch (Exception e) {
            System.out.println("Error al inicializar la base de datos:");
            e.printStackTrace();
        }
        System.out.println("Lanzando interfaz gráfica...");
        Application.launch(HelloApplication.class, args);
    }
    public static void resetearIds() {
        String sql1 = "DELETE FROM coches";
        String sql2 = "DELETE FROM sqlite_sequence WHERE name='coches'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql1);
            stmt.execute(sql2);
            System.out.println("Base de datos reseteada al ID 1.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
