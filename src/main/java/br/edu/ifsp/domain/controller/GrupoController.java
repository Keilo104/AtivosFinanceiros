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
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;

import java.util.Iterator;

public class GrupoController {
    @FXML public ScrollBar scroll;
    @FXML public VBox vBox;

    private Grupo grupo;

    public void init(Grupo group) {
        this.grupo = group;
        updateAtivos();
    }

    private void updateAtivos() {
        vBox.getChildren().clear();
        Iterator<Ativo> iterator = grupo.getIteratorAtivos();
        Ativo ativo;

        while(iterator.hasNext()) {
            ativo = iterator.next();
            ButtonBar bar = new ButtonBar();

            if(ativo instanceof Acao) {
                bar.getButtons().add(new Button("Update"));
            } else {
                bar.getButtons().add(new Button("Alterar"));
            }
            bar.getButtons().add(new Button("Vender"));

            vBox.getChildren().add(bar);
        }
    }
}
