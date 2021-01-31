package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteAcaoDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.application.main.repository.AlphaAdvantageAPIDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    }

    public void init(Usuario usuario) {
        this.usuario = usuario;
    }

    private void configurarCelulasDaTabela() {
        colCodigo.setCellValueFactory( new PropertyValueFactory<>( "codigo" ) );
        colPais.setCellValueFactory( new PropertyValueFactory<>( "pais" ) );
        colNome.setCellValueFactory( new PropertyValueFactory<>( "nome" ) );
    }

    public void btnPesquisar( MouseEvent mouseEvent ) {
        String codigo = txtSigla.getText();
        AlphaAdvantageAPIDAO apiDao = new AlphaAdvantageAPIDAO();
        acoesAPI.addAll( apiDao.search( codigo ) );
        carregarDadosNaTabela();
    }

    private void carregarDadosNaTabela() {
        acoesAPI.clear();
        acoesAPI.addAll( acoesAPI );
        tableView.refresh();
    }

    public void btnAcompanhar( MouseEvent mouseEvent ) {
        Acao acao = tableView.getSelectionModel().getSelectedItem();
        sqliteAcaoDAO sqliteAcaoDao = new sqliteAcaoDAO();
        sqliteAcaoDao.create( acao );
    }
}
