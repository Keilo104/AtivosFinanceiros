package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteAcaoDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteFundoDeInvestimentoDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteRelatorioDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteRendaFixaDAO;
import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import br.edu.ifsp.domain.usecases.relatorio.GerarRelatorioCategoriaUseCase;
import br.edu.ifsp.domain.usecases.utils.CreatePDF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioController {
    @FXML
    ComboBox<String> choiceBoxAtivos;
    @FXML
    CheckBox radioRelatorio;
    @FXML
    DatePicker dataInicial;
    @FXML
    DatePicker dataFinal;
    @FXML
    Button btnGerar;

    public void init() {
        loadComboBox();
    }

    private void loadComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList();

        for ( TipoGrupoEnum tipo : TipoGrupoEnum.values() ) {
            options.add( tipo.getString() );
        }

        choiceBoxAtivos.setItems( options );
    }

    public void ativarPeriodico( ActionEvent actionEvent ) {
        if ( radioRelatorio.isSelected() ) {
            dataInicial.setDisable( false );
            dataFinal.setDisable( false );
        } else {
            dataInicial.setDisable( true );
            dataFinal.setDisable( true );
        }
    }

    public void gerarRelatorio( ActionEvent actionEvent ) {
        String tipoSelecionado = choiceBoxAtivos.getValue();
        Relatorio relatorio = new Relatorio( TipoGrupoEnum.getValueByString( tipoSelecionado ) );
        List<String> relatorioArray = new ArrayList<>();
        String nomeArquivo = "";
        sqliteRelatorioDAO relatorioDAO = new sqliteRelatorioDAO();
        GerarRelatorioCategoriaUseCase gerarRelatorioCategoriaUseCase = new GerarRelatorioCategoriaUseCase( relatorioDAO );
        gerarRelatorioCategoriaUseCase.gerar( relatorio );

        if ( radioRelatorio.isSelected() ) {
            LocalDate inicio = dataInicial.getValue();
            LocalDate fim = dataFinal.getValue();
            if ( checarData( inicio, fim ) ) {
                sqliteRelatorioDAO sqliteRelatorioDAO = new sqliteRelatorioDAO();
                sqliteRelatorioDAO.create( relatorio );
                switch ( relatorio.getCategoria() ) {
                    case ACAO:
                        sqliteAcaoDAO sqliteAcaoDAO = new sqliteAcaoDAO();
                        relatorioArray = sqliteAcaoDAO.gerarRelatorioPeriodo( inicio, fim );
                        nomeArquivo = "Acao Relatório Período";
                        break;
                    case FUNDO_DE_INVESTIMENTO:
                        sqliteFundoDeInvestimentoDAO sqliteFundoDeInvestimentoDAO = new sqliteFundoDeInvestimentoDAO();
                        relatorioArray = sqliteFundoDeInvestimentoDAO.gerarRelatorioPeriodo( inicio, fim );
                        nomeArquivo = "Fundo de Investimento Relatório Período";
                        break;
                    case RENDA_FIXA:
                        sqliteRendaFixaDAO sqliteRendaFixaDAO = new sqliteRendaFixaDAO();
                        relatorioArray = sqliteRendaFixaDAO.gerarRelatorioPeriodo( inicio, fim );
                        nomeArquivo = "Renda Fixa Relatório Período";
                        break;
                }
            }
        } else {
            sqliteRelatorioDAO sqliteRelatorioDAO = new sqliteRelatorioDAO();
            sqliteRelatorioDAO.create( relatorio );

            switch ( relatorio.getCategoria() ) {
                case ACAO:
                    sqliteAcaoDAO sqliteAcaoDAO = new sqliteAcaoDAO();
                    relatorioArray = sqliteAcaoDAO.gerarRelatorio();
                    nomeArquivo = "Acao Relatório";
                    break;
                case FUNDO_DE_INVESTIMENTO:
                    sqliteFundoDeInvestimentoDAO sqliteFundoDeInvestimentoDAO = new sqliteFundoDeInvestimentoDAO();
                    relatorioArray = sqliteFundoDeInvestimentoDAO.gerarRelatorio();
                    nomeArquivo = "Fundo de Investimento Relatório";
                    break;
                case RENDA_FIXA:
                    sqliteRendaFixaDAO sqliteRendaFixaDAO = new sqliteRendaFixaDAO();
                    relatorioArray = sqliteRendaFixaDAO.gerarRelatorio();
                    nomeArquivo = "Renda Fixa Relatório";
                    break;
            }
        }
        CreatePDF createPDF = new CreatePDF();
        createPDF.create( relatorioArray, nomeArquivo );
    }

    private boolean checarData( LocalDate inicio, LocalDate fim ) {
        if ( fim.isAfter( inicio ) ) {
            return true;
        }
        return false;
    }
}
