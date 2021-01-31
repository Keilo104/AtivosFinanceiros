package br.edu.ifsp.domain.controller;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.grupo.GrupoEnum;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaGrupo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.Iterator;

public class PainelController {
    @FXML public TableView<Grupo> tableGrupos;
    @FXML public TableColumn<Grupo, String> cNome;
    @FXML public TableColumn<Grupo, String> cTipo;

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

    private ObservableList<Grupo> grupos;

    public void init(Usuario user) {
        Grupo g1 = new Grupo("1", GrupoEnum.ACAO);
        Grupo g2 = new Grupo("2", GrupoEnum.FUNDO_DE_INVESTIMENTO);
        Grupo g3 = new Grupo("3", GrupoEnum.RENDA_FIXA);
        Grupo g4 = new Grupo("4", GrupoEnum.ACAO);
        Grupo g5 = new Grupo("5", GrupoEnum.RENDA_FIXA);

        user.addAllGrupo(g1, g2, g3, g4, g5);

        usuario = user;
        updateLabels();

        grupos = FXCollections.observableArrayList();
        Iterator<Grupo> iterator = usuario.getIteratorCarteira();
        while(iterator.hasNext()) {
            grupos.add(iterator.next());
        }

        bindTable();
    }

    private void bindTable() {
        cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cTipo.setCellValueFactory(new PropertyValueFactory<>("tipoString"));

        tableGrupos.setItems(grupos);
    }

    private void updateLabels() {
        spanNome.setText(usuario.getNome());
        spanLucroTotal.setText(Float.toString(usuario.getTotalLucrado()));
        spanTotalInvestido.setText(Float.toString(usuario.getTotalInvestido()));
        spanData.setText(LocalDateTime.now().toString());
    }

    private void alertNotSelected() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro :(");
        alert.setContentText("Ação impossível, pois não há nenhum grupo selecionado.");

        alert.showAndWait();
    }

    public void visualizarGrupo() {
        Grupo grupo = tableGrupos.getSelectionModel().getSelectedItem();

        if (grupo != null) {
            JanelaGrupo janelaGrupo = new JanelaGrupo();
            janelaGrupo.showAndWait(grupo);
        } else {
            alertNotSelected();
        }
    }
}
