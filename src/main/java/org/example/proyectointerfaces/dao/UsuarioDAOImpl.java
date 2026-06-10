package org.example.proyectointerfaces.dao;

import org.example.proyectointerfaces.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO{
    @Override
    public boolean login(String usuario, String password) {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?";

        try (PreparedStatement sentencia = conn.prepareStatement(sql)) {
            sentencia.setString(1,usuario);
            sentencia.setString(2,password);
            try (ResultSet rs = sentencia.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Usuario encontrado en la BD.");
                    return true;
                } else {
                    System.out.println("No se ha encontrado a este usuario: '" + usuario + "' con esa contraseña.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("No se ha encontrado a este usuario");
            return false;
        }
    }
}
