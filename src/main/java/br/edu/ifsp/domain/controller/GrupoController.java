package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.AlphaAdvantageAPIDAO;
import br.edu.ifsp.application.main.repository.sqlite.*;
import br.edu.ifsp.domain.DAOs.*;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaAcoes;
import br.edu.ifsp.domain.ui.JanelaFundos;
import br.edu.ifsp.domain.ui.JanelaRendaFixa;
import br.edu.ifsp.domain.usecases.ativo.VendaAtivosUseCase;
import br.edu.ifsp.domain.usecases.ativo.acao.UpdateAPIAcaoUseCase;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Iterator;
import java.util.Optional;

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

    private AtivosDAO ativosDAO;
    private GrupoDAO grupoDAO;
    private LogTransacaoDAO logTransacaoDAO;
    private LogGrupoDAO logGrupoDAO;

    public void init(Usuario user, Grupo group) {
        this.usuario = user;
        this.grupo = group;

        this.acaoDAO = new sqliteAcaoDAO();
        this.logAtivoDAO = new sqliteLogAtivoDAO();
        this.apidao = new AlphaAdvantageAPIDAO();

        this.ativosDAO = new sqliteAtivosDAO();
        this.grupoDAO = new sqliteGrupoDAO();
        this.logTransacaoDAO = new sqliteLogTransacaoDAO();
        this.logGrupoDAO = new sqliteLogGrupoDAO();

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
        int qtd = 1;
        boolean retorno;
        if(grupo.getTipoGrupo() == TipoGrupoEnum.ACAO) {
            qtd = alertQuantityToSell();
            retorno = true;
        } else {
            retorno = alertConfirmacaoSell();
        }

        if(retorno && qtd > 0) {
            VendaAtivosUseCase vendaAtivosUseCase = new VendaAtivosUseCase(ativosDAO, grupoDAO, logTransacaoDAO, logGrupoDAO);
            vendaAtivosUseCase.vendaAtivo(usuario, grupo, ativo, qtd);
            updateAtivos();
            initGrafico();
        }
    }

    private boolean alertConfirmacaoSell() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Tem cereza que deseja vender o ativo?");
        alert.setHeaderText("Você está tentando vender um ativo");
        alert.setContentText("Tem certeza que deseja vender o mesmo?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    private int alertQuantityToSell() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Quantidade para vender");
        dialog.setHeaderText("Quantas ações você deseja vender?");
        dialog.setContentText("Digite:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            return Integer.parseInt(result.get());
        }

        return 0;
    }

    public void adicionarAtivo() {
        switch(grupo.getTipoGrupo()) {
            case ACAO:
                JanelaAcoes janelaAcoes = new JanelaAcoes();
                janelaAcoes.showAndWait( usuario, grupo );
                break;

            case RENDA_FIXA:
                JanelaRendaFixa janelaRendaFixa = new JanelaRendaFixa();
                janelaRendaFixa.showAndWait( usuario, grupo );
                break;

            default:
                JanelaFundos janelaFundos = new JanelaFundos();
                janelaFundos.showAndWait( usuario, grupo );
                break;
        }
        updateAtivos();
        initGrafico();
    }

    public void excluirGrupo() {

    }

    private void initGrafico(){
        GraficoCreator gc = new GraficoCreator();

        graphGrupo.getData().clear();
        XYChart.Series<String,Number> series = gc.setDataGrupo(grupo.getId());

        graphGrupo.getData().add(series);
    }
}
