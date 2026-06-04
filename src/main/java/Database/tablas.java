package Database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class tablas {

    public static void crearTablas() {
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
                    nombre_producto TEXT NOT NULL,
                    categoria TEXT NOT NULL,
                    precio REAL NOT NULL DEFAULT 0.0,
                    stock INTEGER NOT NULL DEFAULT 0
                );
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            if (conn != null) {
                stmt.execute(tablaUsuarios);
                stmt.execute(tablaProductos);
                System.out.println("Tablas 'usuarios' y 'productos' inicializadas con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
    }
}