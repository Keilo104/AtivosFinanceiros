package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.CriarGrupoController;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaCriarGrupo {
    Stage stage = new Stage();

    public void showAndWait(Usuario user) {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/FXMLCriarGrupo.fxml"));
            sceneGraph = loader.load();

            stage.setTitle("Cadastro de novo usuário");
            stage.setScene(new Scene(sceneGraph, 600, 400));
            stage.setResizable( false );

            CriarGrupoController criarGrupoController = (CriarGrupoController) loader.getController();
            criarGrupoController.init(this, user);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }
}
