package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaPainel extends Application {

    private static Scene scene;

    public static void main( String[] args ) {
        launch( args );
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("fxml/FXMLPainel"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
