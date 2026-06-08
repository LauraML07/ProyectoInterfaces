package org.example.proyectointerfaces.dao;

// Asegúrate de que este import apunte a donde Jaime creó la clase de conexión
import org.example.proyectointerfaces.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CocheDAOImpl implements CocheDAO {

    @Override
    public boolean insertar(CochesTablas coche) {
        String sql = "INSERT INTO coches (marca, kilometraje, fecha_matriculacion) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, coche.getMarca());
            pstmt.setDouble(2, coche.getKilometraje());
            pstmt.setString(3, coche.getFechaMatriculacion().toString());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar coche: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<CochesTablas> obtenerTodos() {
        List<CochesTablas> listaCoches = new ArrayList<>();
        String sql = "SELECT * FROM coches";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                CochesTablas coche = new CochesTablas();
                coche.setId(rs.getInt("id"));
                coche.setMarca(rs.getString("marca"));
                coche.setKilometraje(rs.getDouble("kilometraje"));
                coche.setFechaMatriculacion(LocalDate.parse(rs.getString("fecha_matriculacion")));
                listaCoches.add(coche);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los coches: " + e.getMessage());
        }
        return listaCoches;
    }

    @Override
    public boolean actualizar(CochesTablas coche) {
        String sql = "UPDATE coches SET marca = ?, kilometraje = ?, fecha_matriculacion = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, coche.getMarca());
            pstmt.setDouble(2, coche.getKilometraje());
            pstmt.setString(3, coche.getFechaMatriculacion().toString());
            pstmt.setInt(4, coche.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar coche: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM coches WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar coche: " + e.getMessage());
            return false;
        }
    }

    @Override
    public CochesTablas buscarPorId(int id) {
        String sql = "SELECT * FROM coches WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                CochesTablas coche = new CochesTablas();
                coche.setId(rs.getInt("id"));
                coche.setMarca(rs.getString("marca"));
                coche.setKilometraje(rs.getDouble("kilometraje"));
                coche.setFechaMatriculacion(LocalDate.parse(rs.getString("fecha_matriculacion")));
                return coche;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar coche por ID: " + e.getMessage());
        }
        return null;
    }
}