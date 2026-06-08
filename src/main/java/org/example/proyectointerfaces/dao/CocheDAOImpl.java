package org.example.proyectointerfaces.dao;


import org.example.proyectointerfaces.database.CochesTablas;
import org.example.proyectointerfaces.database.DatabaseConnection;
import org.example.proyectointerfaces.database.Tablas;

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
        String sql = "INSERT INTO coches (marca, matricula, fecha_matricula, \"n-puertas\") VALUES (?, ?, ?, ?)";
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
                        rs.getInt("n-puertas")
                );
                listaCoches.add(coche);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los coches: " + e.getMessage());
        }
        return listaCoches;
    }

    @Override
    public boolean actualizar(CochesTablas coche) {
        String sql = "UPDATE coches SET marca = ?, matricula = ?, fecha_matricula = ?, \"n-puertas\" = ? WHERE id = ?";
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
                        rs.getInt("n-puertas")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar coche por ID: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        Tablas.crearTablas(); // Ajusta este nombre si Jaime lo llamó diferente

        // Instanciamos tu super DAO
        CocheDAO cocheDAO = new CocheDAOImpl();

        System.out.println("=== INICIANDO PRUEBAS DEL DAO ===");

        // 1. PRUEBA: INSERTAR
        System.out.println("\n--- 1. Probando INSERTAR ---");
        // Le pasamos un 0 en el ID porque la base de datos es AUTOINCREMENT y se lo asignará sola
        CochesTablas cocheNuevo = new CochesTablas(0, "Toyota", "1234-ABC", LocalDate.now(), 5);
        boolean insertado = cocheDAO.insertar(cocheNuevo);
        System.out.println("¿Coche insertado correctamente? " + insertado);

        // 2. PRUEBA: OBTENER TODOS
        System.out.println("\n--- 2. Probando OBTENER TODOS ---");
        List<CochesTablas> listaCoches = cocheDAO.obtenerTodos();
        for (CochesTablas c : listaCoches) {
            System.out.println("ID: " + c.getId() + " | Marca: " + c.getMarca() + " | Matrícula: " + c.getMatricula() + " | Fecha: " + c.getFecha_matricula());
        }

        // 3. PRUEBA: BUSCAR POR ID y ACTUALIZAR
        if (!listaCoches.isEmpty()) {
            // Cogemos el ID del primer coche que haya en la lista para probar
            int idPrueba = listaCoches.get(0).getId();

            System.out.println("\n--- 3. Probando BUSCAR POR ID (ID: " + idPrueba + ") ---");
            CochesTablas cocheBuscado = cocheDAO.buscarPorId(idPrueba);
            System.out.println("Coche encontrado: " + cocheBuscado.getMarca() + " - " + cocheBuscado.getMatricula());

            System.out.println("\n--- 4. Probando ACTUALIZAR ---");
            // Le cambiamos la marca al coche que acabamos de buscar
            cocheBuscado.setMarca("Honda Actualizado");
            boolean actualizado = cocheDAO.actualizar(cocheBuscado);
            System.out.println("¿Coche actualizado correctamente? " + actualizado);

            // Volvemos a buscarlo para confirmar que se ha guardado el cambio
            CochesTablas cocheComprobacion = cocheDAO.buscarPorId(idPrueba);
            System.out.println("Marca confirmada en BD: " + cocheComprobacion.getMarca());

            System.out.println("\n--- 5. Probando ELIMINAR ---");
            boolean eliminado = cocheDAO.eliminar(idPrueba);
            System.out.println("¿Coche eliminado correctamente? " + eliminado);

        } else {
            System.out.println("\nNo hay coches en la base de datos para probar actualizar o eliminar.");
        }

        System.out.println("\n=== PRUEBAS FINALIZADAS ===");
    }
}