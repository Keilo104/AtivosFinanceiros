package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.LoginController;
import br.edu.ifsp.domain.controller.PainelController;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaLogin {
    private Stage stage = new Stage();

    public void show() {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/FXMLLogin.fxml"));
            sceneGraph = loader.load();

            stage.setTitle("Login");
            stage.setScene(new Scene(sceneGraph, 830, 550));
            stage.setMaxWidth(1080);
            stage.setMaxHeight(720);

            LoginController loginController = (LoginController) loader.getController();
            loginController.init(this);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }
}
