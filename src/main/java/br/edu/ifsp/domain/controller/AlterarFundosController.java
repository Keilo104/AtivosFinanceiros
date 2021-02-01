package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteFundoDeInvestimentoDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteLogAtivoDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteRendaFixaDAO;
import br.edu.ifsp.domain.DAOs.FundoDeInvestimentoDAO;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.DAOs.RendaFixaDAO;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.ui.JanelaAlterarFundos;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.AlterarFundoDeInvestimentoUseCase;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.AlterarRendaFixaUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AlterarFundosController {

    @FXML TextField txtLiquidez;
    @FXML TextField txtRentabilidade;
    @FXML TextField txtTaxa;
    @FXML TextField txtNome;
    @FXML TextField txtValor;

    private FundoDeInvestimentoDAO fundoDeInvestimentoDAO;
    private LogAtivoDAO logAtivoDAO;

    private FundoDeInvestimento fundoDeInvestimento;
    private JanelaAlterarFundos janelaAlterarFundos;

    public void init(FundoDeInvestimento fundoDeInvestimento, JanelaAlterarFundos janelaAlterarFundos) {
        this.fundoDeInvestimento = fundoDeInvestimento;
        this.janelaAlterarFundos = janelaAlterarFundos;

        fundoDeInvestimentoDAO = new sqliteFundoDeInvestimentoDAO();
        logAtivoDAO = new sqliteLogAtivoDAO();

        loadLabels();
    }

    private void loadLabels() {
        txtLiquidez.setText(fundoDeInvestimento.getLiquidez());
        txtRentabilidade.setText(fundoDeInvestimento.getLiquidez());
        txtTaxa.setText(Float.toString(fundoDeInvestimento.getTaxaAdministrativa()));
        txtNome.setText(fundoDeInvestimento.getNome());
        txtValor.setText(Float.toString(fundoDeInvestimento.getValorUnitarioAtual()));
    }

    public void btnAlterar() {
        fundoDeInvestimento.setLiquidez(txtLiquidez.getText());
        fundoDeInvestimento.setRentabilidade(txtRentabilidade.getText());
        fundoDeInvestimento.setTaxaAdministrativa(Float.parseFloat(txtTaxa.getText()));
        fundoDeInvestimento.setNome(txtNome.getText());
        fundoDeInvestimento.setValorUnitarioAtual(Float.parseFloat(txtValor.getText()));

        AlterarFundoDeInvestimentoUseCase alterarFundoDeInvestimentoUseCase = new AlterarFundoDeInvestimentoUseCase(fundoDeInvestimentoDAO, logAtivoDAO);
        alterarFundoDeInvestimentoUseCase.update(fundoDeInvestimento);

        janelaAlterarFundos.close();
    }
}
