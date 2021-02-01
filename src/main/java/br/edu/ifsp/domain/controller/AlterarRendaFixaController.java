package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteLogAtivoDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteRendaFixaDAO;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.DAOs.RendaFixaDAO;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.ui.JanelaAlterarRendaFixa;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.AlterarRendaFixaUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AlterarRendaFixaController {

    @FXML
    TextField txtRendimento;
    @FXML
    DatePicker dataVencimento;
    @FXML
    TextField txtValor;

    private RendaFixa rendaFixa;

    private RendaFixaDAO rendaFixaDAO;
    private LogAtivoDAO logAtivoDAO;

    private JanelaAlterarRendaFixa janelaAlterarRendaFixa;

    public void init( RendaFixa rendaFixa, JanelaAlterarRendaFixa janelaAlterarRendaFixa ) {
        this.rendaFixa = rendaFixa;
        this.janelaAlterarRendaFixa = janelaAlterarRendaFixa;

        this.rendaFixaDAO = new sqliteRendaFixaDAO();
        this.logAtivoDAO = new sqliteLogAtivoDAO();

        loadLabels();
    }

    private void loadLabels() {
        txtRendimento.setText( rendaFixa.getRendimento() );
        txtValor.setText( Float.toString( rendaFixa.getValorTotalAtual() ) );
        dataVencimento.setValue( rendaFixa.getDataVencimento() );
    }

    private void alertSucesso() {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        alert.setTitle( "Você alterou a renda fixa com sucesso! :)" );
        alert.setHeaderText( "Você alterou a renda fixa com sucesso! :)" );
        alert.setContentText( "Você alterou a renda fixa com sucesso! :)" );

        alert.showAndWait();
    }

    public void btnAlterar() {
        try {
            rendaFixa.setRendimento( txtRendimento.getText() );
            rendaFixa.setDataVencimento( rendaFixa.getDataVencimento() );
            rendaFixa.setValorUnitarioAtual( Float.parseFloat( txtValor.getText() ) );

            AlterarRendaFixaUseCase alterarRendaFixaUseCase = new AlterarRendaFixaUseCase( rendaFixaDAO, logAtivoDAO );
            alterarRendaFixaUseCase.update( rendaFixa );

            alertSucesso();
        } catch ( Exception e ) {
            alertException( e );
        }

        janelaAlterarRendaFixa.close();
    }

    private void alertException( Exception e ) {
        Alert alert = new Alert( Alert.AlertType.ERROR );
        alert.setTitle( "Erro de alteração" );
        alert.setHeaderText( "Não foi possível alterar a renda fixa." );
        alert.setContentText( e.getMessage() );

        alert.showAndWait();
    }
}
