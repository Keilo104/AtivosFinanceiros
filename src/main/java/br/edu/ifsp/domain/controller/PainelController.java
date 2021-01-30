package br.edu.ifsp.domain.controller;

import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PainelController {
    @FXML private Button btnRelatorios;
    @FXML private Button btnGrupos;
    @FXML private Button btnAtivos;
    @FXML private LineChart graphAtivos;
    @FXML private Label spanLucroTotal;
    @FXML private Label spanTotalInvestido;
    @FXML private Label spanData;

    private Usuario usuario;

    public void init(Usuario user) {
        this.usuario = user;
    }
}
