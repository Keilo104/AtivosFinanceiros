package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.CtrlToken;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaRecuperar {
    private Stage stage = new Stage();

    public void close() {
        stage.close();
    }

    public void showAndWait() {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/FXMLToken.fxml"));
            sceneGraph = loader.load();

            stage.setTitle("Recuperar a Senha");
            stage.setScene(new Scene(sceneGraph, 600, 400));
            stage.setMaxWidth(600);
            stage.setMaxHeight(400);
            stage.setResizable(false);

            CtrlToken ctrlTokentrlToken = (CtrlToken) loader.getController();
            ctrlTokentrlToken.init(this);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
