package org.example.proyectointerfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectointerfaces.dao.UsuarioDAO;
import org.example.proyectointerfaces.dao.UsuarioDAOImpl;

import static org.example.proyectointerfaces.HelloController.lblBienvenida;

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
        if (ok) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/proyecto2/fxml/menu.fxml"));
                Scene scene = new Scene(loader.load());

                HelloController controller = loader.getController();

                controller.setTexto(LogUsuario.getText());

                Stage stage = (Stage) LogUsuario.getScene().getWindow();
                stage.setScene(scene);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            lblBienvenida.setText("Usuario incorrecto");
        }
    }
}
