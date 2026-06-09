package org.example.proyectointerfaces.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectointerfaces.dao.UsuarioDAO;
import org.example.proyectointerfaces.dao.UsuarioDAOImpl;

public class LoginController {
    @FXML
    public TextField LogUsuario;
    @FXML
    public TextField LogContrasena;
    public Label txtUser;

    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) {
        String usuario = LogUsuario.getText().trim();
        String password = LogContrasena.getText().trim();

        boolean ok = usuarioDAO.login(usuario,password);
        if (ok) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyectointerfaces/crud.fxml"));
                Scene scene = new Scene(loader.load());

                MenuControler controller = loader.getController();

                controller.setTexto(LogUsuario.getText());

                Stage stage = (Stage) LogUsuario.getScene().getWindow();
                stage.setScene(scene);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            txtUser.setText("Usuario incorrecto");
        }
    }
}
