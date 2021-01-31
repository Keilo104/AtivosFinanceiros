package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteAcaoDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.application.main.repository.AlphaAdvantageAPIDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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

    public static ObservableList<Acao> acoesAPI;

    @FXML
    public void initialize() {
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
        sqliteAcaoDAO sqliteAcaoDao = new sqliteAcaoDAO();
        sqliteAcaoDao.create( acao );
    }
}
