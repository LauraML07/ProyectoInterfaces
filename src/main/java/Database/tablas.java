package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class tablas {

    public static void crearTablas() {

        Connection connect = DatabaseConnection.getConnection();

        String tablaUsuarios = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    contraseña TEXT NOT NULL
                );
                """;

        String tablaCoches = """
                CREATE TABLE IF NOT EXISTS coches (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    marca TEXT NOT NULL,
                    matricula TEXT NOT NULL,
                    fecha_matricula TEXT DEFAULT CURRENT_TIMESTAMP,
                    n-puertas TEXT NOT NULL
                );
                """;

        try (Statement stmt = connect.createStatement()) {

           stmt.execute(tablaUsuarios);
           stmt.execute(tablaCoches);
            System.out.println("Base de datos y tablas listas");

        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
    }
}