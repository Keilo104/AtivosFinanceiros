package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.*;
import br.edu.ifsp.domain.DAOs.*;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaFundos;
import br.edu.ifsp.domain.usecases.ativo.CompraAtivosUseCase;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.IncluirFundoDeInvestimentoUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CtrlFundos {

    @FXML
    Label grupoNome;
    @FXML
    TextField txtRentabilidade;
    @FXML
    TextField txtTaxa;
    @FXML
    TextField txtNome;
    @FXML
    TextField txtLiquidez;
    @FXML
    TextField txtValor;

    private Usuario usuario;
    private Grupo grupo;
    private JanelaFundos janelaFundos;

    private AtivosDAO ativosDAO;
    private FundoDeInvestimentoDAO fundoDeInvestimentoDAO;
    private LogAtivoDAO logAtivoDAO;
    private GrupoDAO grupoDAO;
    private LogTransacaoDAO logTransacaoDAO;
    private LogGrupoDAO logGrupoDAO;

    public void init( Usuario usuario, Grupo grupo, JanelaFundos janelaFundos ) {
        this.grupo = grupo;
        this.usuario = usuario;
        this.janelaFundos = janelaFundos;

        this.ativosDAO = new sqliteAtivosDAO();
        this.fundoDeInvestimentoDAO = new sqliteFundoDeInvestimentoDAO();
        this.logAtivoDAO = new sqliteLogAtivoDAO();
        this.grupoDAO = new sqliteGrupoDAO();
        this.logTransacaoDAO = new sqliteLogTransacaoDAO();
        this.logGrupoDAO = new sqliteLogGrupoDAO();

        grupoNome.setText( grupo.getNome() );
    }

    public void btnComprar() {
        float valor = Float.parseFloat( txtValor.getText() );
        float taxa = Float.parseFloat( txtTaxa.getText() );
        String rentabilidade = txtRentabilidade.getText();
        String nome = txtNome.getText();
        String liquidez = txtLiquidez.getText();

        FundoDeInvestimento fundoDeInvestimento = new FundoDeInvestimento( valor, 1, nome, rentabilidade, liquidez, taxa );

        IncluirFundoDeInvestimentoUseCase incluirFundoDeInvestimentoUseCase = new IncluirFundoDeInvestimentoUseCase( fundoDeInvestimentoDAO, logAtivoDAO );
        incluirFundoDeInvestimentoUseCase.include( fundoDeInvestimento );

        CompraAtivosUseCase compraAtivosUseCase = new CompraAtivosUseCase( ativosDAO, grupoDAO, logTransacaoDAO, logGrupoDAO );
        compraAtivosUseCase.compraAtivo( usuario, grupo, fundoDeInvestimento );

        alertSucesso();
        janelaFundos.close();
    }

    private void alertSucesso() {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        alert.setTitle( "Você comprou o fundo de investimento com sucesso! :)" );
        alert.setHeaderText( "Você comprou o fundo de investimento com sucesso! :)" );
        alert.setContentText( "Você comprou o fundo de investimento com sucesso! :)" );

        alert.showAndWait();
    }
}
