package org.example.proyectointerfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectointerfaces.dao.CocheDAO;
import org.example.proyectointerfaces.dao.CocheDAOImpl;
import org.example.proyectointerfaces.database.CochesTablas;

import java.time.LocalDate;

public class HelloController {
    @FXML
    public TextField InsMarca;
    @FXML
    public TextField InsMatricula;
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
    public TextField ActuPuertas;
    @FXML
    public TextField BuscarId;
    @FXML
    public static Label lblBienvenida;
    CocheDAO cocheDAO = new CocheDAOImpl();

    public static void setTexto(String usuario) {
        lblBienvenida.setText("Bienvenido, " + usuario);
    }

    @FXML
    public void onInsertButtonClick(ActionEvent actionEvent) {
        String marca = InsMarca.getText();
        String matricula = InsMatricula.getText();
        LocalDate fecha = LocalDate.now();
        int nPuertas = Integer.parseInt(InsPuertas.getText());

        CochesTablas coche = new CochesTablas(0,marca,matricula,fecha,nPuertas);
        cocheDAO.insertar(coche);
    }

    @FXML
    public void onEliminarButtonClick(ActionEvent actionEvent) {
        int id = Integer.parseInt(EliminarId.getText());
        cocheDAO.eliminar(id);
    }

    @FXML
    public void onActualizarButtonClick(ActionEvent actionEvent) {
        int id = Integer.parseInt(ActuId.getText());
        String marca = ActuMarca.getText();
        String matricula = ActuMatricula.getText();
        LocalDate fecha = LocalDate.now();
        int nPuertas = Integer.parseInt(ActuPuertas.getText());
        CochesTablas coche = new CochesTablas(0,marca,matricula,fecha,nPuertas);
        cocheDAO.actualizar(coche);
    }

    @FXML
    public void onBuscarPorIDButtonClick(ActionEvent actionEvent) {
        int id = Integer.parseInt(BuscarId.getText());
        System.out.println(cocheDAO.buscarPorId(id));
    }

    @FXML
    public void onIrTablaButtonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("org/example/proyectointerfaces/Tablas.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error al abrir la ventana de la tabla:");
            e.printStackTrace();
        }
    }
}