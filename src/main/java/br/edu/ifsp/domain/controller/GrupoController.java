package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.*;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.DAOs.AtivosDAO;
import br.edu.ifsp.domain.usecases.ativo.CompraAtivosUseCase;
import br.edu.ifsp.domain.DAOs.AcaoDAO;
import br.edu.ifsp.domain.usecases.ativo.acao.IncluirAcaoUseCase;
import br.edu.ifsp.domain.DAOs.GrupoDAO;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.DAOs.LogGrupoDAO;
import br.edu.ifsp.domain.DAOs.LogTransacaoDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Iterator;

public class GrupoController {
    @FXML public VBox vBox;
    @FXML public Label labelNome;
    @FXML public Label labelTipo;
    @FXML public CategoryAxis xAxis;
    @FXML public NumberAxis yAxis;
    @FXML public LineChart<String,Number> graphGrupo;

    private Usuario usuario;
    private Grupo grupo;

    public void init(Usuario user, Grupo group) {
        this.usuario = user;
        this.grupo = group;

        initGrafico();
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
        ((Acao) ativo).updateFromAPI();
        System.out.println("Updatando API para " + ativo.toString());
    }

    private void updateButton(Ativo ativo) {
        System.out.println("Updatando sem API para " + ativo.toString());

    }

    private void sellButton(Ativo ativo) {
        System.out.println("Vendendo " + ativo.toString());
    }

    public void adicionarAtivo() {
        AtivosDAO ativosDAO = new sqliteAtivosDAO();
        AcaoDAO acaoDAO = new sqliteAcaoDAO();
        LogAtivoDAO logAtivoDAO = new sqliteLogAtivoDAO();
        GrupoDAO grupoDAO = new sqliteGrupoDAO();
        LogTransacaoDAO logTransacaoDAO = new sqliteLogTransacaoDAO();
        LogGrupoDAO logGrupoDAO = new sqliteLogGrupoDAO();
        IncluirAcaoUseCase incluirAcaoUseCase = new IncluirAcaoUseCase(acaoDAO, logAtivoDAO);

        Acao nova = new Acao(12, 5, "GME", "USA");
        incluirAcaoUseCase.include(nova);

        CompraAtivosUseCase compraAtivosUseCase = new CompraAtivosUseCase(ativosDAO, grupoDAO, logTransacaoDAO, logGrupoDAO);
        compraAtivosUseCase.compraAtivo(usuario, grupo, nova);
        updateAtivos();
    }

    public void excluirGrupo() {

    }

    private void initGrafico(){
        GraficoCreator gc = new GraficoCreator();
        XYChart.Series<String,Number> series = gc.setDataGrupo(grupo.getId());

        graphGrupo.getData().add(series);
    }
}
