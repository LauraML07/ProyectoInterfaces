package org.example.proyectointerfaces.dao;

import org.example.proyectointerfaces.database.UsserTablas;

public interface UsuarioDAO {
    boolean login(String username, String password);
}
