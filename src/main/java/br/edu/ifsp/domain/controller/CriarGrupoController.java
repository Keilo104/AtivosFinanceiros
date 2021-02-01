package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteGrupoDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteLogGrupoDAO;
import br.edu.ifsp.domain.DAOs.GrupoDAO;
import br.edu.ifsp.domain.DAOs.LogGrupoDAO;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaCriarGrupo;
import br.edu.ifsp.domain.usecases.grupo.CriarGrupoUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CriarGrupoController {
    @FXML
    public TextField txtFieldGrupo;
    @FXML
    public ChoiceBox<String> choiceBoxCategoria;

    private JanelaCriarGrupo janelaCriarGrupo;
    private Usuario user;
    private GrupoDAO grupoDAO;
    private LogGrupoDAO logGrupoDAO;

    public void init( JanelaCriarGrupo janelaCriarGrupo, Usuario user ) {
        this.janelaCriarGrupo = janelaCriarGrupo;
        this.user = user;

        this.grupoDAO = new sqliteGrupoDAO();
        this.logGrupoDAO = new sqliteLogGrupoDAO();

        loadComboBox();
    }

    private void loadComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList();

        for ( TipoGrupoEnum tipo : TipoGrupoEnum.values() ) {
            options.add( tipo.getString() );
        }

        choiceBoxCategoria.setItems( options );
    }

    public void btnCriar( ActionEvent actionEvent ) {
        String tipoSelecionado = choiceBoxCategoria.getValue();
        TipoGrupoEnum tipo = TipoGrupoEnum.getValueByString( tipoSelecionado );

        Grupo grupo = new Grupo( txtFieldGrupo.getText(), tipo, user.getCpf() );

        CriarGrupoUseCase criarGrupoUseCase = new CriarGrupoUseCase( this.grupoDAO, this.logGrupoDAO );
        criarGrupoUseCase.include( user, grupo );

        janelaCriarGrupo.close();
    }

    public void btnCancelar( ActionEvent actionEvent ) {
        janelaCriarGrupo.close();
    }
}
