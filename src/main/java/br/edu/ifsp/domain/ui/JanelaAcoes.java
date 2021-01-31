package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.CtrlAcoes;
import br.edu.ifsp.domain.controller.CtrlAcompanharAcao;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaAcoes {
    private Stage stage = new Stage();

    public void show( Grupo grupo ) {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader( App.class.getResource( "fxml/FXMLAcoes.fxml" ) );
            sceneGraph = loader.load();


            stage.setTitle( "Visualizar Ações" );
            stage.setScene( new Scene( sceneGraph, 750, 670 ) );
            stage.setMaxWidth( 1080 );
            stage.setMaxHeight( 720 );

            CtrlAcoes ctrlAcoes = new CtrlAcoes();
            ctrlAcoes.init( grupo );

            stage.show();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }
}
