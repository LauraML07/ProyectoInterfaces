package org.example.proyectointerfaces.dao;

import org.example.proyectointerfaces.database.modelos.CochesTablas;

import java.util.List;

public interface CocheDAO {
    boolean insertar(CochesTablas coche);
    List<CochesTablas> obtenerTodos();
    boolean actualizar(CochesTablas coche);
    boolean eliminar(int id);
    CochesTablas buscarPorId(int id);
}
