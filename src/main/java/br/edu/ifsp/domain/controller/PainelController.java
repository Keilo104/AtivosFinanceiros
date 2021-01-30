package br.edu.ifsp.domain.controller;

import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaGrupo;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class PainelController {
    @FXML public Button btnRelatorios;
    @FXML public Button btnGrupos;
    @FXML public Button btnAtivos;
    @FXML public LineChart graphAtivos;
    @FXML public Label spanNome;
    @FXML public Label spanLogOut;
    @FXML public Label spanLucroTotal;
    @FXML public Label spanTotalInvestido;
    @FXML public Label spanData;

    private Usuario usuario;

    public void init(Usuario user) {
        usuario = user;

        updateLabels();
    }

    private void updateLabels() {
        spanNome.setText(usuario.getNome());
        spanLucroTotal.setText(Float.toString(usuario.getTotalLucrado()));
        spanTotalInvestido.setText(Float.toString(usuario.getTotalInvestido()));
        spanData.setText(LocalDate.now().toString());
    }

    public void showGrupos() {
        JanelaGrupo janelaGrupo = new JanelaGrupo();
        janelaGrupo.showAndWait(usuario);
    }
}
