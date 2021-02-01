package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteLogAtivoDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteRendaFixaDAO;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.DAOs.RendaFixaDAO;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.ui.JanelaAlterarRendaFixa;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.AlterarRendaFixaUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AlterarRendaFixaController {

    @FXML TextField txtRendimento;
    @FXML DatePicker dataVencimento;
    @FXML TextField txtValor;

    private RendaFixa rendaFixa;

    private RendaFixaDAO rendaFixaDAO;
    private LogAtivoDAO logAtivoDAO;

    private JanelaAlterarRendaFixa janelaAlterarRendaFixa;

    public void init(RendaFixa rendaFixa, JanelaAlterarRendaFixa janelaAlterarRendaFixa) {
        this.rendaFixa = rendaFixa;
        this.janelaAlterarRendaFixa = janelaAlterarRendaFixa;

        this.rendaFixaDAO = new sqliteRendaFixaDAO();
        this.logAtivoDAO = new sqliteLogAtivoDAO();

        loadLabels();
    }

    private void loadLabels() {
        txtRendimento.setText(rendaFixa.getRendimento());
        txtValor.setText(Float.toString(rendaFixa.getValorTotalAtual()));
        dataVencimento.setValue(rendaFixa.getDataVencimento());
    }

    public void btnAlterar() {
        rendaFixa.setRendimento(txtRendimento.getText());
        rendaFixa.setDataVencimento(rendaFixa.getDataVencimento());
        rendaFixa.setValorUnitarioAtual(Float.parseFloat(txtValor.getText()));

        AlterarRendaFixaUseCase alterarRendaFixaUseCase = new AlterarRendaFixaUseCase(rendaFixaDAO, logAtivoDAO);
        alterarRendaFixaUseCase.update(rendaFixa);

        janelaAlterarRendaFixa.close();
    }
}
