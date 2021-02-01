package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.PainelController;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaPainel {
    public void show( Usuario user ) {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader( App.class.getResource( "fxml/FXMLPainel.fxml" ) );
            sceneGraph = loader.load();

            Stage stage = new Stage();
            stage.setTitle( "Painel Dashboard" );
            stage.setScene( new Scene( sceneGraph, 915, 705 ) );
            stage.setMinWidth( 800 );
            stage.setMinHeight( 400 );

            PainelController painelController = ( PainelController ) loader.getController();
            painelController.init( user );

            stage.show();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
