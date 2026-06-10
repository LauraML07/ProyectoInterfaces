package org.example.proyectointerfaces.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.proyectointerfaces.dao.CocheDAO;
import org.example.proyectointerfaces.dao.CocheDAOImpl;
import org.example.proyectointerfaces.database.modelos.CochesTablas;

import java.util.List;

public class TablaController {
    @FXML
    public TableView<CochesTablas> Tablas;
    @FXML
    public TableColumn<CochesTablas, Integer> ColId;
    @FXML
    public TableColumn<CochesTablas,String> ColMarca;
    @FXML
    public TableColumn<CochesTablas,String> ColMatricula;
    @FXML
    public TableColumn<CochesTablas,String> ColFecha;
    @FXML
    public TableColumn<CochesTablas,Integer> ColPuertas;

    private String usuarioConectado;
    private CocheDAO cocheDAO = new CocheDAOImpl();
    private ObservableList<CochesTablas> listaCoches = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        ColMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        ColFecha.setCellValueFactory(new PropertyValueFactory<>("fecha_matricula"));
        ColPuertas.setCellValueFactory(new PropertyValueFactory<>("n_puertas"));

        Tablas.setItems(listaCoches);
        cargarDatosDesdeBD();
    }
    private void cargarDatosDesdeBD() {
        listaCoches.clear();

        List<CochesTablas> listaBD = cocheDAO.obtenerTodos();
        listaCoches.addAll(listaBD);
    }


    @FXML
    public void onVolverMenuButtonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyectointerfaces/crud.fxml"));
            Scene scene = new Scene(loader.load());
            String css = getClass().getResource("/css/style.css").toExternalForm();
            scene.getStylesheets().add(css);
            MenuControler controller = loader.getController();
            controller.setTexto(usuarioConectado);

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error al volver al menú:");
            e.printStackTrace();
        }
    }
}
