package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.CriarGrupoController;
import br.edu.ifsp.domain.controller.RelatorioController;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaRelatorio {
    Stage stage = new Stage();
    public void showAndWait(Usuario user, ObservableList<Grupo> grupos) {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/FXMLRelatorios.fxml"));
            sceneGraph = loader.load();

            stage.setTitle("Gerar relatório");
            stage.setScene(new Scene(sceneGraph, 500, 500));
            stage.setMinWidth(800);
            stage.setMinHeight(400);

            RelatorioController relatorioController = (RelatorioController) loader.getController();
            relatorioController.init(this,user,grupos);

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
