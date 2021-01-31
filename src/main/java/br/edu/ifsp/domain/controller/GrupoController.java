package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.AlphaAdvantageAPIDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteAcaoDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteLogAtivoDAO;
import br.edu.ifsp.domain.DAOs.APIDAO;
import br.edu.ifsp.domain.DAOs.AcaoDAO;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaAcoes;
import br.edu.ifsp.domain.ui.JanelaFundos;
import br.edu.ifsp.domain.ui.JanelaRendaFixa;
import br.edu.ifsp.domain.usecases.ativo.acao.UpdateAPIAcaoUseCase;
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

    private AcaoDAO acaoDAO;
    private LogAtivoDAO logAtivoDAO;
    private APIDAO apidao;

    public void init(Usuario user, Grupo group) {
        this.usuario = user;
        this.grupo = group;

        this.acaoDAO = new sqliteAcaoDAO();
        this.logAtivoDAO = new sqliteLogAtivoDAO();
        this.apidao = new AlphaAdvantageAPIDAO();

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

            if(grupo.getTipoGrupo() == TipoGrupoEnum.ACAO) {
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
        UpdateAPIAcaoUseCase updateAPIAcaoUseCase = new UpdateAPIAcaoUseCase(acaoDAO, logAtivoDAO, apidao);
        updateAPIAcaoUseCase.update((Acao) ativo);
    }

    private void updateButton(Ativo ativo) {
        System.out.println("Updatando sem API para " + ativo.toString());

    }

    private void sellButton(Ativo ativo) {
        System.out.println("Vendendo " + ativo.toString());
    }

    public void adicionarAtivo() {
        switch(grupo.getTipoGrupo()) {
            case ACAO:
                JanelaAcoes janelaAcoes = new JanelaAcoes();
                janelaAcoes.showAndWait( grupo );
                break;

            case RENDA_FIXA:
                JanelaRendaFixa janelaRendaFixa = new JanelaRendaFixa();
                janelaRendaFixa.showAndWait( grupo );
                break;

            default:
                JanelaFundos janelaFundos = new JanelaFundos();
                janelaFundos.showAndWait( grupo );
                break;
        }

    }

    public void excluirGrupo() {

    }

    private void initGrafico(){
        GraficoCreator gc = new GraficoCreator();
        XYChart.Series<String,Number> series = gc.setDataGrupo(grupo.getId());

        graphGrupo.getData().add(series);
    }
}
