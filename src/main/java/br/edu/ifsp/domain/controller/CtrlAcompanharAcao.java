package br.edu.ifsp.domain.controller;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.usecases.ativo.acao.APIDAO;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class CtrlAcompanharAcao {

    public TextField txtSigla;

    public void btnAcompanhar( MouseEvent mouseEvent ) {
        String codigo = txtSigla.getText();
        APIDAO apiDao = new APIDAO();
        List<Acao> acoes = new ArrayList<>();
        acoes = apiDao.search( codigo );




    }
}
