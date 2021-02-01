package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteRelatorioDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteRelatorioPeriodoDAO;
import br.edu.ifsp.domain.DAOs.GrupoDAO;
import br.edu.ifsp.domain.DAOs.RelatorioDAO;
import br.edu.ifsp.domain.DAOs.RelatorioPeriodoDAO;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import br.edu.ifsp.domain.entities.relatorio.RelatorioPeriodo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaCriarGrupo;
import br.edu.ifsp.domain.ui.JanelaGrupo;
import br.edu.ifsp.domain.ui.JanelaRelatorio;
import br.edu.ifsp.domain.usecases.relatorio.GerarRelatorioCategoriaUseCase;
import br.edu.ifsp.domain.usecases.relatorio.GerarRelatorioPeriodoUseCase;
import br.edu.ifsp.domain.usecases.utils.CreatePDF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public class RelatorioController {
    @FXML
    public TableView<Grupo> tableRelatorioGrupo;
    @FXML public TableColumn<Grupo, String> cGrupo;
    @FXML public TableColumn<Grupo, String> cTipo;
    @FXML public CheckBox radioRelatorio;
    @FXML public DatePicker dataInicial;
    @FXML public DatePicker dataFinal;
    @FXML public Button btnGerar;

    private JanelaRelatorio janelaRelatorio;
    private Usuario usuario;
    private ObservableList<Grupo> grupos;
    private RelatorioDAO relatorioDAO;
    private RelatorioPeriodoDAO relatorioPeriodoDAO;

    @FXML
    public void init(JanelaRelatorio janelaRelatorio, Usuario user,ObservableList<Grupo> grupos) {
        this.grupos = grupos;
        this.janelaRelatorio = janelaRelatorio;
        usuario = user;
        configurarCelulasDaTabela();

        this.relatorioDAO = new sqliteRelatorioDAO();
        this.relatorioPeriodoDAO = new sqliteRelatorioPeriodoDAO();
    }

    private void configurarCelulasDaTabela() {
        cGrupo.setCellValueFactory( new PropertyValueFactory<>( "nome" ) );
        cTipo.setCellValueFactory( new PropertyValueFactory<>( "tipoString" ) );
        tableRelatorioGrupo.setItems(grupos);
    }

    public void ativarPeriodico(ActionEvent actionEvent) {
        if(radioRelatorio.isSelected()){
            dataInicial.setDisable(false);
            dataFinal.setDisable(false);
        } else {
            dataInicial.setDisable(true);
            dataFinal.setDisable(true);
        }
    }

    public void ativarCampos(MouseEvent mouseEvent) {
        Grupo grupo = tableRelatorioGrupo.getSelectionModel().getSelectedItem();
        if (grupo != null) {
            btnGerar.setDisable(false);
        } else {
            btnGerar.setDisable(true);
        }
    }

    public void gerarRelatorio(ActionEvent actionEvent) {
        Grupo grupo = tableRelatorioGrupo.getSelectionModel().getSelectedItem();
        if(radioRelatorio.isSelected()){
            Relatorio relatorio = new Relatorio(TipoGrupoEnum.valueOf(cTipo.getText()));

            //GerarRelatorioCategoriaUseCase gerarRelatorioCategoriaUseCase = new GerarRelatorioCategoriaUseCase(relatorioDAO);
            //gerarRelatorioCategoriaUseCase.gerar(relatorio);
        } else{
            RelatorioPeriodo relatorioPeriodo = new
                RelatorioPeriodo(TipoGrupoEnum.valueOf(cTipo.getText()),dataInicial.getValue(),dataFinal.getValue());


            //GerarRelatorioPeriodoUseCase gerarRelatorioPeriodoUseCase = new GerarRelatorioPeriodoUseCase(relatorioDAO);
            //gerarRelatorioPeriodoUseCase.gerarRelatorioPeriodo(relatorioPeriodo);
        }

        //CreatePDF createPDF = new CreatePDF();
        //createPDF.create();



    }
}
