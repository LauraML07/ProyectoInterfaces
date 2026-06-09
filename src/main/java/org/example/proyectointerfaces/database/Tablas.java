package org.example.proyectointerfaces.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

public class Tablas {

    public static void crearTablas() {

        Connection connect = DatabaseConnection.getConnection();

        String tablaUsuarios = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL UNIQUE,
                    contrasena TEXT NOT NULL
                );
                """;

        String tablaCoches = """
                CREATE TABLE IF NOT EXISTS coches (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    marca TEXT NOT NULL,
                    matricula TEXT NOT NULL,
                    fecha_matricula TEXT DEFAULT CURRENT_TIMESTAMP,
                    n_puertas TEXT NOT NULL
                );
                """;
        String usuario1 = """
                INSERT OR IGNORE INTO usuarios (nombre, contrasena) VALUES (?,?);
                """;

        try (Statement stmt = connect.createStatement()) {

           stmt.execute(tablaUsuarios);
           stmt.execute(tablaCoches);
           System.out.println("Base de datos y tablas listas");
           try (PreparedStatement sentencia = connect.prepareStatement(usuario1)){
               sentencia.setString(1,"usuario");
               sentencia.setString(2,"usuario");
               int filasAfectadas = sentencia.executeUpdate();

               if (filasAfectadas > 0) {
                   System.out.println("Usuario inicial 'usuario' creado con éxito.");
               } else {
                   System.out.println("El usuario inicial ya existía. Saltando inserción.");
               }
           }

        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
    }
}
