package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.*;
import br.edu.ifsp.domain.DAOs.*;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaRendaFixa;
import br.edu.ifsp.domain.usecases.ativo.CompraAtivosUseCase;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.IncluirRendaFixaUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CtrlRendaFixa {

    @FXML Label grupoNome;
    @FXML TextField txtRendimento;
    @FXML TextField txtValor;
    @FXML DatePicker dataVencimento;

    private Usuario usuario;
    private Grupo grupo;
    private JanelaRendaFixa janelaRendaFixa;

    private AtivosDAO ativosDAO;
    private RendaFixaDAO rendaFixaDAO;
    private LogAtivoDAO logAtivoDAO;
    private GrupoDAO grupoDAO;
    private LogTransacaoDAO logTransacaoDAO;
    private LogGrupoDAO logGrupoDAO;

    public void init(Usuario usuario, Grupo grupo, JanelaRendaFixa janelaRendaFixa) {
        this.grupo = grupo;
        this.usuario = usuario;
        this.janelaRendaFixa = janelaRendaFixa;

        this.ativosDAO = new sqliteAtivosDAO();
        this.rendaFixaDAO = new sqliteRendaFixaDAO();
        this.logAtivoDAO = new sqliteLogAtivoDAO();
        this.grupoDAO = new sqliteGrupoDAO();
        this.logTransacaoDAO = new sqliteLogTransacaoDAO();
        this.logGrupoDAO = new sqliteLogGrupoDAO();

        grupoNome.setText(grupo.getNome());
    }

    public void btnComprar() {
        float valor = Float.parseFloat(txtValor.getText());
        String rendimento = txtRendimento.getText();
        LocalDate data = dataVencimento.getValue();

        RendaFixa rendaFixa = new RendaFixa(valor, 1, rendimento, data);

        IncluirRendaFixaUseCase incluirRendaFixaUseCase = new IncluirRendaFixaUseCase(ativosDAO, rendaFixaDAO, logAtivoDAO);
        incluirRendaFixaUseCase.include(rendaFixa);

        CompraAtivosUseCase compraAtivosUseCase = new CompraAtivosUseCase(ativosDAO, grupoDAO, logTransacaoDAO, logGrupoDAO);
        compraAtivosUseCase.compraAtivo(usuario, grupo, rendaFixa);

        alertSucesso();
        janelaRendaFixa.close();
    }

    private void alertSucesso() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Você comprou a renda fixa com sucesso! :)");
        alert.setHeaderText("Você comprou a renda fixa com sucesso! :)");
        alert.setContentText("Você comprou a renda fixa com sucesso! :)");

        alert.showAndWait();
    }
}
