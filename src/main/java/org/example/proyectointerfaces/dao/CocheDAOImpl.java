package org.example.proyectointerfaces.dao;


import org.example.proyectointerfaces.database.modelos.CochesTablas;
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
        String sql = "INSERT INTO coches (marca, matricula, fecha_matricula, n_puertas) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, coche.getMarca());
            pstmt.setString(2, coche.getMatricula());
            pstmt.setString(3, coche.getFecha_matricula().toString());
            pstmt.setInt(4, coche.getN_puertas());

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
                CochesTablas coche = new CochesTablas(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("matricula"),
                        LocalDate.parse(rs.getString("fecha_matricula")),
                        rs.getInt("n_puertas")
                );
                listaCoches.add(coche);
                System.out.println("Coche cargado correctamente en memoria: " + coche.getMarca());
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los coches ");
            e.printStackTrace();
        }
        return listaCoches;
    }

    @Override
    public boolean actualizar(CochesTablas coche) {
        String sql = "UPDATE coches SET marca = ?, matricula = ?, fecha_matricula = ?, n_puertas = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, coche.getMarca());
            pstmt.setString(2, coche.getMatricula());
            pstmt.setString(3, coche.getFecha_matricula().toString());
            pstmt.setInt(4, coche.getN_puertas());
            pstmt.setInt(5, coche.getId());

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
                return new CochesTablas(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("matricula"),
                        LocalDate.parse(rs.getString("fecha_matricula")),
                        rs.getInt("n_puertas")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar coche por ID: " + e.getMessage());
        }
        return null;
    }
}