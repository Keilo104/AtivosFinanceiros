package br.edu.ifsp.domain.controller;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;

import java.util.Iterator;

public class GrupoController {
    @FXML public VBox vBox;
    @FXML public Label labelNome;
    @FXML public Label labelTipo;

    private Grupo grupo;

    public void init(Grupo group) {
        this.grupo = group;
        updateAtivos();
        updateLabels();
    }

    private void updateLabels() {
        labelNome.setText(grupo.getNome());
        labelTipo.setText(grupo.getTipoString());
    }

    private void updateAtivos() {
        vBox.getChildren().clear();
        Iterator<Ativo> iterator = grupo.getIteratorAtivos();
        Ativo ativo;

        while(iterator.hasNext()) {
            ativo = iterator.next();
            ButtonBar bar = new ButtonBar();

            Label nome = new Label(ativo.getNome());
            bar.getButtons().add(nome);

            if(ativo instanceof Acao) {
                Button update = new Button("Update");
                Ativo finalAtivo = ativo;
                update.setOnAction(e -> updateAPIButton(finalAtivo));

                bar.getButtons().add(update);
            } else {
                Button alterar = new Button("Alterar");
                Ativo finalAtivo = ativo;
                alterar.setOnAction(e -> updateButton(finalAtivo));

                bar.getButtons().add(alterar);
            }

            Button sell = new Button("Vender");
            Ativo finalAtivo = ativo;
            sell.setOnAction(e -> sellButton(finalAtivo));

            bar.getButtons().add(sell);

            vBox.getChildren().add(bar);
        }
    }

    private void updateAPIButton(Ativo ativo) {
        System.out.println("Updatando API para " + ativo.toString());
    }

    private void updateButton(Ativo ativo) {
        System.out.println("Updatando sem API para " + ativo.toString());

    }

    private void sellButton(Ativo ativo) {
        System.out.println("Vendendo " + ativo.toString());
    }
}
