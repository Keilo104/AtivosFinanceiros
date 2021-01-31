package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.CtrlAcoes;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaAcoes {
    private Stage stage = new Stage();

    public void showAndWait( Grupo grupo ) {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader( App.class.getResource( "fxml/FXMLAcoes.fxml" ) );
            sceneGraph = loader.load();

            stage.setTitle( "Visualizar Ações" );
            stage.setScene( new Scene( sceneGraph, 750, 450 ) );
            stage.setMinWidth( 755 );
            stage.setMinHeight( 450 );
            stage.setMaxWidth( 1080 );
            stage.setMaxHeight( 1080 );

            CtrlAcoes ctrlAcoes = new CtrlAcoes();
            ctrlAcoes.init( grupo );

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
