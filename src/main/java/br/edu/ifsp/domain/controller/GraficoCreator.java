package br.edu.ifsp.domain.controller;


import br.edu.ifsp.application.main.repository.sqlite.sqliteLogGrupoDAO;
import br.edu.ifsp.domain.entities.log.LogGrupo;
import br.edu.ifsp.domain.usecases.log.loggrupo.LogGrupoDAO;
import javafx.scene.chart.XYChart;

import java.util.List;

public class GraficoCreator {
    sqliteLogGrupoDAO logGrupoDAO = new sqliteLogGrupoDAO();
    public XYChart.Series<String,Number> setDataPainel(){
        List<LogGrupo> logs = logGrupoDAO.findAllOrderByData();

        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        series.setName("Minha Carteira");
        float ini = 0;
        for (LogGrupo log : logs){
            String data = log.getData().toString();
            float valor = ini+log.getMudanca();
            series.getData().add(new XYChart.Data(data ,valor));
            System.out.println(series.getData());
            ini = valor;
        }
    return series;
    }
}
