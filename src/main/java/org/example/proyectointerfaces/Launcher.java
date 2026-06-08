package org.example.proyectointerfaces;

import org.example.proyectointerfaces.database.Tablas;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        Tablas.crearTablas();
    }

    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
    }
}
