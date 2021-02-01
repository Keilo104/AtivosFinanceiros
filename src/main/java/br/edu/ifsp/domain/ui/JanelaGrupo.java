package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.GrupoController;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaGrupo {
    public void showAndWait( Usuario usuario, Grupo grupo ) {
        ScrollPane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader( App.class.getResource( "fxml/FXMLGrupo.fxml" ) );
            sceneGraph = loader.load();

            Stage stage = new Stage();
            stage.setTitle( "Painel de Grupos" );
            stage.setScene( new Scene( sceneGraph, 615, 600 ) );
            stage.setMinWidth( 615 );
            stage.setMinHeight( 602 );
            stage.setMaxWidth( 617 );

            GrupoController grupoController = ( GrupoController ) loader.getController();
            grupoController.init( usuario, grupo );

            stage.initModality( Modality.APPLICATION_MODAL );
            stage.showAndWait();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
