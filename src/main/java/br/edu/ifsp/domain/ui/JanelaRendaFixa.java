package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.CtrlRendaFixa;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaRendaFixa {
    private Stage stage = new Stage();

    public void showAndWait( Usuario usuario, Grupo grupo ) {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader( App.class.getResource( "fxml/FXMLRendaFixa.fxml" ) );
            sceneGraph = loader.load();

            stage.setTitle( "Adicionar Renda Fixa" );
            stage.setScene( new Scene( sceneGraph, 590, 160 ) );
            stage.setResizable( false );

            CtrlRendaFixa ctrlRendaFixa = ( CtrlRendaFixa ) loader.getController();
            ctrlRendaFixa.init( usuario, grupo, this );

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
