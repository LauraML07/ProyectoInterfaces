package org.example.proyectointerfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button onHelloButtonClick;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onByeButtonClick(ActionEvent actionEvent) {

    }

    public void onInsertButtonClick(ActionEvent actionEvent) {
    }

    public void onEliminarButtonClick(ActionEvent actionEvent) {
    }

    public void onActualizarButtonClick(ActionEvent actionEvent) {
    }

    public void onBuscarPorIDButtonClick(ActionEvent actionEvent) {

    }

    public void onIrTablaButtonClick(ActionEvent actionEvent) {
    }
}
