package br.edu.ifsp.domain.ui.login;

import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class JanelaLogin {
    public void showAndWait( Usuario usuario ) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Pane graph = loader.load( getClass().getResource( "./FXMLLogin.fxml" ).openStream() );
            Scene scene = new Scene( graph, 520, 250 );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
