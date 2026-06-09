package org.example.proyectointerfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.proyectointerfaces.dao.UsuarioDAO;
import org.example.proyectointerfaces.dao.UsuarioDAOImpl;

public class CONTROLLER_LOGIN {
    @FXML
    public TextField LogUsuario;
    @FXML
    public TextField LogContrasena;

    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) {
        String usuario = LogUsuario.getText();
        String password = LogContrasena.getText();

        boolean ok = usuarioDAO.login(usuario,password);

    }
}
