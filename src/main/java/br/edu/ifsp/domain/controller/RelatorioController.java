package br.edu.ifsp.domain.controller;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaCriarGrupo;
import br.edu.ifsp.domain.ui.JanelaGrupo;
import br.edu.ifsp.domain.ui.JanelaRelatorio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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

    @FXML
    public void init(JanelaRelatorio janelaRelatorio, Usuario user,ObservableList<Grupo> grupos) {
        this.grupos = grupos;
        this.janelaRelatorio = janelaRelatorio;
        usuario = user;
        configurarCelulasDaTabela();
    }

    private void configurarCelulasDaTabela() {
        cGrupo.setCellValueFactory( new PropertyValueFactory<>( "nome" ) );
        cTipo.setCellValueFactory( new PropertyValueFactory<>( "tipoString" ) );
        tableRelatorioGrupo.setItems(grupos);
    }

    public void gerarRelatorio(ActionEvent actionEvent) {

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
}
