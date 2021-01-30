package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteGrupoDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteLogGrupoDAO;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaCriarGrupo;
import br.edu.ifsp.domain.usecases.grupo.CriarGrupoUseCase;
import br.edu.ifsp.domain.usecases.grupo.GrupoDAO;
import br.edu.ifsp.domain.usecases.log.loggrupo.LogGrupoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CriarGrupoController {
    @FXML public TextField txtFieldGrupo;
    @FXML public ChoiceBox<String> choiceBoxCategoria;

    private JanelaCriarGrupo janelaCriarGrupo;
    public Usuario user;

    public void init(JanelaCriarGrupo janelaCriarGrupo, Usuario user) {
        this.janelaCriarGrupo = janelaCriarGrupo;
        this.user = user;

        loadComboBox();
    }

    private void loadComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList();

        for(TipoGrupoEnum tipo : TipoGrupoEnum.values()) {
            options.add(tipo.getString());
        }

        choiceBoxCategoria.setItems(options);
    }

    public void btnCriar( ActionEvent actionEvent ) {
        String tipoSelecionado = choiceBoxCategoria.getValue();
        TipoGrupoEnum tipo = TipoGrupoEnum.getValueByString(tipoSelecionado);

        Grupo grupo = new Grupo(txtFieldGrupo.getText(), tipo);

        GrupoDAO grupoDAO = new sqliteGrupoDAO();
        LogGrupoDAO logGrupoDAO = new sqliteLogGrupoDAO();

        CriarGrupoUseCase criarGrupoUseCase = new CriarGrupoUseCase(grupoDAO, logGrupoDAO);
        criarGrupoUseCase.include(user, grupo);

        janelaCriarGrupo.close();
    }

    public void btnCancelar( ActionEvent actionEvent ) {
        janelaCriarGrupo.close();
    }
}
