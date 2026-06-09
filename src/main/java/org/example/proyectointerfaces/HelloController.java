package org.example.proyectointerfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class HelloController {
    @FXML
    public TextField InsMarca;
    @FXML
    public TextField InsMatricula;
    @FXML
    public TextField InsFecha;
    @FXML
    public TextField InsPuertas;
    @FXML
    public TextField EliminarId;
    @FXML
    public TextField ActuId;
    @FXML
    public TextField ActuMarca;
    @FXML
    public TextField ActuMatricula;
    @FXML
    public TextField ActuFecha;
    @FXML
    public TextField ActuPuertas;
    @FXML
    public TextField BuscarId;

    @FXML
    public void onInsertButtonClick(ActionEvent actionEvent) {
        String marca = InsMarca.getText();
        String matricula = InsMatricula.getText();
        LocalDate fecha = LocalDate.now();

    }

    @FXML
    public void onEliminarButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onActualizarButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onBuscarPorIDButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onIrTablaButtonClick(ActionEvent actionEvent) {
    }
}
