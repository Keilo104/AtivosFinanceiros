package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.GrupoController;
import br.edu.ifsp.domain.controller.PainelController;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaGrupo {
    public void showAndWait(Usuario user) {
        ScrollPane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/FXMLGrupo.fxml"));
            sceneGraph = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Painel de Grupos");
            stage.setScene(new Scene(sceneGraph, 800, 700));
            stage.setMinWidth(800);
            stage.setMinHeight(400);

            GrupoController grupoController = (GrupoController) loader.getController();
            grupoController.init(user);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
