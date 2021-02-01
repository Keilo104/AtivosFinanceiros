package br.edu.ifsp.domain.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class HistoricoAcaoController {
    @FXML
    public LineChart<String, Number> chartHistorico;
    @FXML
    public Label labelCodigo;
    private String codigo;

    public void init( String codigo ) {
        this.codigo = codigo;
        initGrafico();
        labelCodigo.setText( codigo );
    }

    private void initGrafico() {
        GraficoCreator gc = new GraficoCreator();
        XYChart.Series<String, Number> series = gc.setDataAcao( codigo );

        chartHistorico.getData().add( series );
    }
}
