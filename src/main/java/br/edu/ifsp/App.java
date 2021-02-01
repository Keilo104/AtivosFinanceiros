package br.edu.ifsp;

import br.edu.ifsp.domain.ui.JanelaLogin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    public static void main( String[] args ) {
        launch();
    }

    @Override
    public void start( Stage stage ) throws IOException {
        JanelaLogin janelaLogin = new JanelaLogin();
        janelaLogin.show();
    }
}