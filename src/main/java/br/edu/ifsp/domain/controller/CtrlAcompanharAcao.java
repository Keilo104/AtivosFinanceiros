package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.AlphaAdvantageAPIDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteAcaoDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteLogAtivoDAO;
import br.edu.ifsp.domain.DAOs.AcaoDAO;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.ativo.acao.IncluirAcaoUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class CtrlAcompanharAcao {

    public Button btnAcompanhar;
    @FXML TextField txtSigla;
    @FXML TableView<Acao> tableView;
    @FXML TableColumn<Acao, String> colCodigo;
    @FXML TableColumn<Acao, String> colNome;
    @FXML TableColumn<Acao, String> colPais;

    private Usuario usuario;

    private AcaoDAO acaoDAO;
    private LogAtivoDAO logAtivoDAO;

    public static ObservableList<Acao> acoesAPI;

    @FXML
    public void initialize() {
        this.acaoDAO = new sqliteAcaoDAO();
        this.logAtivoDAO = new sqliteLogAtivoDAO();

        configurarCelulasDaTabela();
        inserirDadosAFonte();
    }

    public void init(Usuario usuario) {
        this.usuario = usuario;
    }

    private void configurarCelulasDaTabela() {
        colCodigo.setCellValueFactory( new PropertyValueFactory<>( "codigo" ) );
        colPais.setCellValueFactory( new PropertyValueFactory<>( "pais" ) );
        colNome.setCellValueFactory( new PropertyValueFactory<>( "nome" ) );
    }

    private void inserirDadosAFonte() {
        acoesAPI = FXCollections.observableArrayList();
        tableView.setItems( acoesAPI );
    }

    private void carregarDadosNaTabela(List<Acao> acoes) {
        acoesAPI.clear();
        acoesAPI.addAll( acoes );
        tableView.refresh();
    }

    public void btnPesquisar( ActionEvent actionEvent ) {
        String codigo = txtSigla.getText().toUpperCase().trim();
        List<Acao> acao = new ArrayList<>();
        AlphaAdvantageAPIDAO apiDao = new AlphaAdvantageAPIDAO();
        acao =  apiDao.search( codigo );
        carregarDadosNaTabela(acao);
    }

    public void btnAcompanhar( ActionEvent actionEvent ) {
        Acao acao = tableView.getSelectionModel().getSelectedItem();
        IncluirAcaoUseCase incluirAcaoUseCase = new IncluirAcaoUseCase(acaoDAO, logAtivoDAO);
        incluirAcaoUseCase.include(acao);
        alertExcluirGrupo(acao);
    }

    private void alertExcluirGrupo( Acao acao ) {
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle( "Acompanhar ação" );
        alert.setHeaderText( "A ação " + acao.getNome() + " foi adicionada à sua lista de ações." );
        alert.setContentText( "Para comprá-la, abra as ações a partir de um grupo." );
        alert.showAndWait();
    }
}
