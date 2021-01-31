package br.edu.ifsp.domain.controller;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaAcompanharAcao;
import br.edu.ifsp.domain.ui.JanelaCriarGrupo;
import br.edu.ifsp.domain.ui.JanelaGrupo;
import br.edu.ifsp.domain.ui.JanelaLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Iterator;

public class PainelController {
    @FXML public TableView<Grupo> tableGrupos;
    @FXML public TableColumn<Grupo, String> cNome;
    @FXML public TableColumn<Grupo, String> cTipo;

    @FXML public Button btnRelatorios;
    @FXML public CategoryAxis xAxis;
    @FXML public NumberAxis yAxis;
    @FXML public LineChart<String,Number> graphAtivos;
    @FXML public Label spanNome;
    @FXML public Label spanLucroTotal;
    @FXML public Label spanTotalInvestido;
    @FXML public Label spanData;
    @FXML Button btnSair;

    private Usuario usuario;

    private ObservableList<Grupo> grupos;

    public void init(Usuario user) {
        usuario = user;
        updateLabels();

        grupos = FXCollections.observableArrayList();

        initGrafico();
        updateTable();
        bindTable();
    }

    private void updateTable() {
        grupos.clear();

        Iterator<Grupo> iterator = usuario.getIteratorCarteira();
        while(iterator.hasNext()) {
            grupos.add(iterator.next());
        }
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
        spanData.setText( LocalDate.now().toString());
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
            janelaGrupo.showAndWait(usuario, grupo);
        } else {
            alertNotSelected();
        }
    }

    public void criarGrupo() {
        JanelaCriarGrupo janelaCriarGrupo = new JanelaCriarGrupo();
        janelaCriarGrupo.showAndWait(usuario);

        updateTable();
    }

    private void initGrafico(){
        GraficoCreator gc = new GraficoCreator();
        XYChart.Series<String,Number> series = gc.setDataPainel();

        graphAtivos.getData().add(series);
    }

    public void abrirJanelaTracking( ActionEvent actionEvent ) {
        JanelaAcompanharAcao janelaAcompanharAcao = new JanelaAcompanharAcao();
        janelaAcompanharAcao.show( usuario );
    }
    
}
