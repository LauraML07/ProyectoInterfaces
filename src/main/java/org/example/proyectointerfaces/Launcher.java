package org.example.proyectointerfaces;

import Database.tablas;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        tablas.crearTablas();
    }

    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
    }
}
