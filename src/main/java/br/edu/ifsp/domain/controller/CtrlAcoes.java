package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteAcaoDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class CtrlAcoes {
    @FXML TableView<Acao> tableView;
    @FXML TableColumn<Acao, String> colCodigo;
    @FXML TableColumn<Acao, String> colPais;
    @FXML TableColumn<Acao, String> colNome;
    @FXML TableColumn<Acao, Float> colPreco;
    @FXML TextField txtCodigo;
    @FXML TextField txtNome;
    @FXML TextField txtPais;
    @FXML TextField txtValor;
    @FXML TextField txtQuantia;
    @FXML TextField txtValorTotal;

    public static ObservableList<Acao> acoes;

    @FXML
    public void initialize() {
        configurarCelulasDaTabela();
        inserirDadosAFonte();
        carregarDadosNaTabela();
    }

    private void configurarCelulasDaTabela() {
        colCodigo.setCellValueFactory( new PropertyValueFactory<>( "codigo" ) );
        colPais.setCellValueFactory( new PropertyValueFactory<>( "pais" ) );
        colNome.setCellValueFactory( new PropertyValueFactory<>( "nome" ) );
        colPreco.setCellValueFactory( new PropertyValueFactory<>( "valorUnitarioAtual" ) );
    }

    private void inserirDadosAFonte() {
        acoes = FXCollections.observableArrayList();
        tableView.setItems( acoes );
    }

    private void carregarDadosNaTabela() {
        acoes.clear();
        List<Acao> acoesDB = new ArrayList<>();
        acoesDB = recuperarAcoesDB();
        acoes.addAll( acoesDB );
        tableView.refresh();
    }

    private List<Acao> recuperarAcoesDB() {
        sqliteAcaoDAO acaoDAO = new sqliteAcaoDAO();
        return acaoDAO.findAll();
    }

    public void mostrarDadosAcaoNoTextField( MouseEvent mouseEvent ) {
        Acao acao = tableView.getSelectionModel().getSelectedItem();
        txtCodigo.setText( acao.getCodigo() );
        txtNome.setText( acao.getNome() );
        txtValor.setText( String.valueOf( acao.getValorUnitarioAtual() ) );
    }

    public void btnComprar( ActionEvent actionEvent ) {
        Acao acao = tableView.getSelectionModel().getSelectedItem();
        Grupo grupo = new Grupo();
    }
}
