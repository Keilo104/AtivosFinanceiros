package br.edu.ifsp.domain.ui;

import br.edu.ifsp.domain.controller.PainelController;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaPainel {
    public void show(Usuario user) {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader();
            sceneGraph = loader.load(getClass().getResource("fxml/FXMLPainel.fxml").openStream());

            Stage stage = new Stage();
            stage.setTitle("Painel Dashboard");
            stage.setScene(new Scene(sceneGraph, 800, 700));
            stage.setMinWidth(800);
            stage.setMinHeight(400);

            PainelController painelController = new PainelController();
            painelController.init(user);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
