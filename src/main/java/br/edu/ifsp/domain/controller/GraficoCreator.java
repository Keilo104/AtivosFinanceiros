package br.edu.ifsp.domain.controller;


import br.edu.ifsp.application.main.repository.AlphaAdvantageAPIDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteLogGrupoDAO;
import br.edu.ifsp.domain.entities.log.LogGrupo;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
            ini = valor;
        }
    return series;
    }

    public XYChart.Series<String,Number> setDataGrupo(int idGrupo){
        List<LogGrupo> logs = logGrupoDAO.findOneGrupoOrderByData(idGrupo);

        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        series.setName("Grupo");
        float ini = 0;
        for (LogGrupo log : logs){
            String data = log.getData().toString();
            float valor = ini+log.getMudanca();
            series.getData().add(new XYChart.Data(data ,valor));
            ini = valor;
        }
        return series;
    }

    public XYChart.Series<String,Number> setDataAcao(String codigo){
        AlphaAdvantageAPIDAO alpha = new AlphaAdvantageAPIDAO();
        Map<String, Float>  historico = alpha.getHistoryOfOne(codigo);
        TreeMap<String, Float> sorted = new TreeMap<>();
        sorted.putAll(historico);
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        series.setName("Ação");
        int flag = 0;
        for (Map.Entry<String, Float> e: sorted.entrySet()){
            if(flag>85){
                String data = e.getKey();
                float valor = e.getValue();
                series.getData().add(new XYChart.Data(data, valor));
            }
            flag++;
        }
        return series;
    }
}
