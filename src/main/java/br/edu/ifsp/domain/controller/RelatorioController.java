package br.edu.ifsp.domain.controller;

import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.Date;

public class RelatorioController {
    @FXML ComboBox<String> choiceBoxAtivos;
    @FXML CheckBox radioRelatorio;
    @FXML DatePicker dataInicial;
    @FXML DatePicker dataFinal;
    @FXML Button btnGerar;

    public void init() {
        loadComboBox();
    }

    private void loadComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList();

        for(TipoGrupoEnum tipo : TipoGrupoEnum.values()) {
            options.add(tipo.getString());
        }

        choiceBoxAtivos.setItems(options);
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

    public void gerarRelatorio(ActionEvent actionEvent) {
        String tipoSelecionado = choiceBoxAtivos.getValue();
        if(radioRelatorio.isSelected()){
            Relatorio relatorio = new Relatorio(TipoGrupoEnum.valueOf(tipoSelecionado.replace( " ", "_" ).toUpperCase()));
            LocalDate inicio = dataInicial.getValue();
            LocalDate fim = dataFinal.getValue();
            if (checarData(inicio, fim)) {
                //GerarRelatorioCategoriaUseCase gerarRelatorioCategoriaUseCase = new GerarRelatorioCategoriaUseCase(relatorioDAO);
                //gerarRelatorioCategoriaUseCase.gerar(relatorio);
            }
        } else {
//            RelatorioPeriodo relatorioPeriodo = new
//                RelatorioPeriodo(TipoGrupoEnum.valueOf(cTipo.getText()),dataInicial.getValue(),dataFinal.getValue());

            //GerarRelatorioPeriodoUseCase gerarRelatorioPeriodoUseCase = new GerarRelatorioPeriodoUseCase(relatorioDAO);
            //gerarRelatorioPeriodoUseCase.gerarRelatorioPeriodo(relatorioPeriodo);
        }
        //CreatePDF createPDF = new CreatePDF();
        //createPDF.create();
    }

    private boolean checarData( LocalDate inicio, LocalDate fim ) {
        if ( inicio.isAfter( fim )) {
            return true;
        }
        return false;
    }
}
