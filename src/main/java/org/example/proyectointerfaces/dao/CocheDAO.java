package org.example.proyectointerfaces.dao;

import java.util.List;

public interface CocheDAO {
    boolean insertar(CochesTablas coche);
    List<CochesTablas> obtenerTodos();
    boolean actualizar(CochesTablas coche);
    boolean eliminar(int id);
    CochesTablas buscarPorId(int id);
}
