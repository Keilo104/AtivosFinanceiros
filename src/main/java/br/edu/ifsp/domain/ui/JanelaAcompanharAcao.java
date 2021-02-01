package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.CtrlAcompanharAcao;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaAcompanharAcao {
    private Stage stage = new Stage();

    public void show( Usuario usuario ) {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader( App.class.getResource( "fxml/FXMLAcompanharAcao.fxml" ) );
            sceneGraph = loader.load();

            stage.setTitle( "Pesquisar Ação" );
            stage.setScene( new Scene( sceneGraph, 600, 580 ) );
            stage.setMaxWidth( 1080 );
            stage.setMaxHeight( 720 );

            CtrlAcompanharAcao ctrlAcompanharAcao = ( CtrlAcompanharAcao ) loader.getController();
            ctrlAcompanharAcao.init( usuario );

            stage.show();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }
}
