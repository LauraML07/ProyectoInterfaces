package org.example.proyectointerfaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyectointerfaces/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        try {
            String css = getClass().getResource("/css/style.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo CSS, continuando sin estilos...");
        }
        stage.setTitle("Acceso al Sistema");
        stage.setScene(scene);
        stage.show();
    }
}
