package br.edu.ifsp.domain.ui;

import br.edu.ifsp.App;
import br.edu.ifsp.domain.controller.AlterarFundosController;
import br.edu.ifsp.domain.controller.CtrlAcoes;
import br.edu.ifsp.domain.controller.CtrlFundos;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaAlterarFundos {
    private Stage stage = new Stage();

    public void showAndWait(FundoDeInvestimento fundoDeInvestimento) {
        Pane sceneGraph = null;

        try {
            FXMLLoader loader = new FXMLLoader( App.class.getResource( "fxml/FXMLAlterarFundos.fxml" ) );
            sceneGraph = loader.load();

            stage.setTitle( "Alterar Fundo de Investimento" );
            stage.setScene( new Scene( sceneGraph, 560, 210 ) );
            stage.setResizable(false);

            AlterarFundosController alterarFundosController = (AlterarFundosController) loader.getController();
            alterarFundosController.init( fundoDeInvestimento, this );

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
