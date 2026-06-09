package org.example.proyectointerfaces.dao;

import org.example.proyectointerfaces.database.DatabaseConnection;
import org.example.proyectointerfaces.database.UsserTablas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO{
    @Override
    public boolean login(String username, String password) {
        Connection conexion = DatabaseConnection.getConnection();
        boolean existe = false;
        String sql = """
                SELECT * FROM usuarios
                WHERE nombre = ? AND contrasena = ?
                """;

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1,username);
            sentencia.setString(2,password);
            if (sentencia.executeUpdate()!=0)
                existe = true;
        } catch (SQLException e) {
            System.out.println("No se ha encontrado a este usuario");
        }
        return existe;
    }
}
