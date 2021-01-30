package br.edu.ifsp.domain.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;

public class GrupoController {
    @FXML
    public ScrollBar scroll;

    @FXML
    public VBox vbox;

    public GrupoController() {

    }

    public void botaolegal() {
        scroll.setMin(0);
        scroll.setMax(100);
        scroll.setValue(50);

        scroll.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                vbox.setLayoutY(-new_val.doubleValue());
            }
        });
    }

}
