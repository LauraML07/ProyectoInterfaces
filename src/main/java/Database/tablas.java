package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class tablas {

    public static void crearTablas() {

        String url = "jdbc:sqlite:db/database_beta.db";

        String tablaUsuarios = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    correo TEXT UNIQUE NOT NULL,
                    contraseña TEXT NOT NULL,
                    fecha_nacimiento DATETIME DEFAULT CURRENT_TIMESTAMP
                );
                """;

        String tablaProductos = """
                CREATE TABLE IF NOT EXISTS tareas (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    marca TEXT NOT NULL,
                    matricula TEXT NOT NULL,
                    fecha_matricula DATETIME DEFAULT CURRENT_TIMESTAMP
                );
                """;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

           stmt.execute(tablaUsuarios);
           stmt.execute(tablaProductos);
            System.out.println("Base de datos y tablas listas");

        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
    }
}