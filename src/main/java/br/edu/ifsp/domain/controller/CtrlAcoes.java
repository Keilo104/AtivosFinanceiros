package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.*;
import br.edu.ifsp.domain.DAOs.AtivosDAO;
import br.edu.ifsp.domain.DAOs.GrupoDAO;
import br.edu.ifsp.domain.DAOs.LogGrupoDAO;
import br.edu.ifsp.domain.DAOs.LogTransacaoDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.application.main.repository.AlphaAdvantageAPIDAO;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaAcoes;
import br.edu.ifsp.domain.usecases.ativo.CompraAtivosUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.List;

public class CtrlAcoes {
    @FXML Label grupoNome;
    @FXML TableView<Acao> tableView;
    @FXML TableColumn<Acao, String> colCodigo;
    @FXML TableColumn<Acao, String> colPais;
    @FXML TableColumn<Acao, String> colNome;
    @FXML TextField txtCodigo;
    @FXML TextField txtNome;
    @FXML TextField txtPais;
    @FXML TextField txtValor;
    @FXML TextField txtQuantia;
    @FXML TextField txtValorTotal;

    private Usuario usuario;
    private Grupo grupo;

    private AtivosDAO ativosDAO;
    private GrupoDAO grupoDAO;
    private LogTransacaoDAO logTransacaoDAO;
    private LogGrupoDAO logGrupoDAO;

    private JanelaAcoes janela;

    public static ObservableList<Acao> acoes;

    @FXML
    public void initialize() {
        ativosDAO = new sqliteAtivosDAO();
        grupoDAO = new sqliteGrupoDAO();
        logTransacaoDAO = new sqliteLogTransacaoDAO();
        logGrupoDAO = new sqliteLogGrupoDAO();

        configurarCelulasDaTabela();
        inserirDadosAFonte();
        carregarDadosNaTabela();
    }

    public void init( Usuario usuario, Grupo grupo, JanelaAcoes janela ) {
        this.grupo = grupo;
        this.usuario = usuario;
        this.janela = janela;

        grupoNome.setText(grupo.getNome());
    }

    private void configurarCelulasDaTabela() {
        colCodigo.setCellValueFactory( new PropertyValueFactory<>( "codigo" ) );
        colPais.setCellValueFactory( new PropertyValueFactory<>( "pais" ) );
        colNome.setCellValueFactory( new PropertyValueFactory<>( "nome" ) );
    }

    private void inserirDadosAFonte() {
        acoes = FXCollections.observableArrayList();
        tableView.setItems( acoes );
    }

    private void carregarDadosNaTabela() {
        acoes.clear();
        List<Acao> acoesDB = recuperarAcoesDB();
        acoes.addAll( acoesDB );
        tableView.refresh();
    }

    private List<Acao> recuperarAcoesDB() {
        sqliteAcaoDAO acaoDAO = new sqliteAcaoDAO();
        return acaoDAO.findAll();
    }

    public void mostrarDadosAcaoNoTextField() {
        Acao acao = tableView.getSelectionModel().getSelectedItem();
        txtCodigo.setText( acao.getCodigo() );
        txtNome.setText( acao.getNome() );
        txtPais.setText( acao.getPais() );

        AlphaAdvantageAPIDAO apiDao = new AlphaAdvantageAPIDAO();
        Acao valorAcao = apiDao.getOne( acao.getCodigo() );
        txtValor.setText( String.valueOf( valorAcao.getValorUnitarioAtual() ) );
    }

    public void btnComprar() {
        Acao acaoTabela = tableView.getSelectionModel().getSelectedItem();

        acaoTabela.setValorUnitarioAtual(Float.parseFloat(txtValor.getText()));
        acaoTabela.setValorUnitarioComprado(Float.parseFloat(txtValor.getText()));
        acaoTabela.setQuantidade(Integer.parseInt(txtQuantia.getText()));
        acaoTabela.setDataComprado(LocalDateTime.now());

        CompraAtivosUseCase compraAtivosUseCase = new CompraAtivosUseCase(ativosDAO, grupoDAO, logTransacaoDAO, logGrupoDAO);
        compraAtivosUseCase.compraAtivo(usuario, grupo, acaoTabela);

        alertSucesso();
        this.janela.close();
    }

    private void alertSucesso() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Você comprou a ação com sucesso! :)");
        alert.setHeaderText("Você comprou a ação ativo com sucesso! :)");
        alert.setContentText("Você comprou a ação ativo com sucesso! :)");

        alert.showAndWait();
    }

    public void updateTotal() {
        if(txtQuantia.getText().equals("") || txtQuantia.getText().matches("[a-zA-Z]+")) {
            txtValorTotal.setText("");
        } else {
            float valor = Float.parseFloat(txtValor.getText()) * Float.parseFloat(txtQuantia.getText());
            txtValorTotal.setText(Float.toString(valor));
        }
    }
}
