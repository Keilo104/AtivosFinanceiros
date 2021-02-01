package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.HistoricoAcaoController;
import br.edu.ifsp.domain.controller.PainelController;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaHistoricoAcao {
    private Stage stage = new Stage();
    public void close() {
        stage.close();
    }

    public void showAndWait(String codigo)  {
        Pane sceneGraph = null;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/FXMLHistoricoAcao.fxml"));
            sceneGraph = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Painel Dashboard");
            stage.setScene(new Scene(sceneGraph, 600, 470));
            stage.setMinWidth(600);
            stage.setMinHeight(470);
            stage.setResizable(false);

            HistoricoAcaoController historicoAcaoController = (HistoricoAcaoController) loader.getController();
            historicoAcaoController.init(codigo);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
