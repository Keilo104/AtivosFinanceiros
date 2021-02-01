package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.CadastrarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaCadastrar {
    Stage stage = new Stage();

    public void showAndWait() {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader( App.class.getResource( "fxml/FXMLCadastrar.fxml" ) );
            sceneGraph = loader.load();

            stage.setTitle( "Cadastro de novo usuário" );
            stage.setScene( new Scene( sceneGraph, 750, 600 ) );
            stage.setResizable( false );

            CadastrarController cadastrarController = ( CadastrarController ) loader.getController();
            cadastrarController.init( this );

            stage.initModality( Modality.APPLICATION_MODAL );
            stage.showAndWait();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }
}
