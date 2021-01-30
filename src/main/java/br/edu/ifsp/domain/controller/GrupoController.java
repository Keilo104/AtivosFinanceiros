package br.edu.ifsp.domain.controller;

import br.edu.ifsp.domain.entities.usuario.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;

public class GrupoController {
    @FXML public ScrollBar scroll;
    @FXML public VBox vbox;

    private Usuario usuario;

    public void init(Usuario user) {
        usuario = user;
    }

}
