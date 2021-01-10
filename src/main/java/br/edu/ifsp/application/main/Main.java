package br.edu.ifsp.application.main;

import br.edu.ifsp.application.main.repository.*;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.ativo.AtivosDAO;
import br.edu.ifsp.domain.usecases.ativo.CompraAtivosUseCase;
import br.edu.ifsp.domain.usecases.ativo.VendaAtivosUseCase;
import br.edu.ifsp.domain.usecases.ativo.acao.AcaoDAO;
import br.edu.ifsp.domain.usecases.ativo.acao.AlterarAcaoUseCase;
import br.edu.ifsp.domain.usecases.ativo.acao.ExcluirAcaoUseCase;
import br.edu.ifsp.domain.usecases.ativo.acao.IncluirAcaoUseCase;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.AlterarFundoDeInvestimentoUseCase;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.ExcluirFundoDeInvestimentoUseCase;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.FundoDeInvestimentoDAO;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.IncluirFundoDeInvestimentoUseCase;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.AlterarRendaFixaUserCase;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.ExcluirRendaFixaUseCase;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.IncluirRendaFixaUseCase;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.RendaFixaDAO;
import br.edu.ifsp.domain.usecases.grupo.AtualizarGrupoUseCase;
import br.edu.ifsp.domain.usecases.grupo.CriarGrupoUseCase;
import br.edu.ifsp.domain.usecases.grupo.ExcluirGrupoUseCase;
import br.edu.ifsp.domain.usecases.grupo.GrupoDAO;
import br.edu.ifsp.domain.usecases.log.logativo.LogAtivoDAO;
import br.edu.ifsp.domain.usecases.log.logativo.SalvarHistoricoAtivoUseCase;
import br.edu.ifsp.domain.usecases.log.logtransacao.LogTransacaoDAO;
import br.edu.ifsp.domain.usecases.log.logtransacao.SalvarHistoricoTransacaoUseCase;
import br.edu.ifsp.domain.usecases.relatorio.GerarRelatorioCategoriaUseCase;
import br.edu.ifsp.domain.usecases.relatorio.GerarRelatorioPeriodoUseCase;
import br.edu.ifsp.domain.usecases.relatorio.RelatorioDAO;
import br.edu.ifsp.domain.usecases.usuario.*;

import java.util.Iterator;

public class Main {
    //ativo
    private static CompraAtivosUseCase compraAtivosUseCase;
    private static VendaAtivosUseCase vendaAtivosUseCase;

    // acao
    private static AlterarAcaoUseCase alterarAcaoUseCase;
    private static ExcluirAcaoUseCase excluirAcaoUseCase;
    private static IncluirAcaoUseCase incluirAcaoUseCase;

    //fundo de investimento
    private static AlterarFundoDeInvestimentoUseCase alterarFundoDeInvestimentoUseCase;
    private static ExcluirFundoDeInvestimentoUseCase excluirFundoDeInvestimentoUseCase;
    private static IncluirFundoDeInvestimentoUseCase incluirFundoDeInvestimentoUseCase;

    //renda fixa
    private static AlterarRendaFixaUserCase alterarRendaFixaUserCase;
    private static ExcluirRendaFixaUseCase excluirRendaFixaUseCase;
    private static IncluirRendaFixaUseCase incluirRendaFixaUseCase;

    //grupo
    private static AtualizarGrupoUseCase atualizarGrupoUseCase;
    private static CriarGrupoUseCase criarGrupoUseCase;
    private static ExcluirGrupoUseCase excluirGrupoUseCase;

    //log ativo
    private static SalvarHistoricoAtivoUseCase salvarHistoricoAtivoUseCase;

    //log transacao
    private static SalvarHistoricoTransacaoUseCase salvarHistoricoTransacaoUseCase;

    //relatorio
    private static GerarRelatorioCategoriaUseCase gerarRelatorioCategoriaUseCase;
    private static GerarRelatorioPeriodoUseCase gerarRelatorioPeriodoUseCase;

    //usuario
    private static CadastroUseCase cadastroUseCase;
    private static LoginUseCase loginUseCase;
    private static RecuperarSenhaUseCase recuperarSenhaUseCase;

    private static void configureInjection(){
        //DAOs
        AtivosDAO ativosDAO = new InMemoryAtivosDAO();
        AcaoDAO acaoDAO = new InMemoryAcaoDAO();
        FundoDeInvestimentoDAO fundoDeInvestimentoDAO = new InMemoryFundoDeInvestimentoDAO();
        GrupoDAO grupoDAO = new InMemoryGrupoDAO();
        LogAtivoDAO logAtivoDAO = new InMemoryLogAtivoDAO();
        LogTransacaoDAO logTransacaoDAO = new InMemoryLogTransacaoDAO();
        RelatorioDAO relatorioDAO = new InMemoryRelatorioDAO();
        RendaFixaDAO rendaFixaDAO = new InMemoryRendaFixaDAO();
        TokenDAO tokenDAO = new InMemoryTokenDAO();
        UsuarioDAO usuarioDAO = new InMemoryUsuarioDAO();

        //ativo
        compraAtivosUseCase = new CompraAtivosUseCase(ativosDAO, grupoDAO);
        vendaAtivosUseCase = new VendaAtivosUseCase(ativosDAO, grupoDAO);

        //acao
        alterarAcaoUseCase = new AlterarAcaoUseCase(acaoDAO, logAtivoDAO);
        excluirAcaoUseCase = new ExcluirAcaoUseCase(acaoDAO, logAtivoDAO);
        incluirAcaoUseCase = new IncluirAcaoUseCase(acaoDAO, logAtivoDAO);

        //fundo de investimento
        alterarFundoDeInvestimentoUseCase = new AlterarFundoDeInvestimentoUseCase(fundoDeInvestimentoDAO, logAtivoDAO);
        excluirFundoDeInvestimentoUseCase = new ExcluirFundoDeInvestimentoUseCase(fundoDeInvestimentoDAO, logAtivoDAO);
        incluirFundoDeInvestimentoUseCase = new IncluirFundoDeInvestimentoUseCase(fundoDeInvestimentoDAO, logAtivoDAO);

        //renda fixa
        alterarRendaFixaUserCase = new AlterarRendaFixaUserCase(rendaFixaDAO, logAtivoDAO);
        excluirRendaFixaUseCase = new ExcluirRendaFixaUseCase(rendaFixaDAO, logAtivoDAO);
        incluirRendaFixaUseCase = new IncluirRendaFixaUseCase(rendaFixaDAO, logAtivoDAO);

        //grupo
        atualizarGrupoUseCase = new AtualizarGrupoUseCase(grupoDAO);
        criarGrupoUseCase = new CriarGrupoUseCase(grupoDAO);
        excluirGrupoUseCase = new ExcluirGrupoUseCase(grupoDAO);

        //log ativo
        salvarHistoricoAtivoUseCase = new SalvarHistoricoAtivoUseCase(logAtivoDAO);

        //log transacao
        salvarHistoricoTransacaoUseCase = new SalvarHistoricoTransacaoUseCase(logTransacaoDAO);

        //relatorio
        gerarRelatorioCategoriaUseCase = new GerarRelatorioCategoriaUseCase(relatorioDAO);
        gerarRelatorioPeriodoUseCase = new GerarRelatorioPeriodoUseCase(relatorioDAO);

        //usuario
        cadastroUseCase = new CadastroUseCase(usuarioDAO);
        loginUseCase = new LoginUseCase(usuarioDAO);
        recuperarSenhaUseCase = new RecuperarSenhaUseCase(usuarioDAO, tokenDAO);
    }

    private static void printCarteira(Iterator<Grupo> carteira) {
        while (carteira.hasNext()) {
            System.out.println(carteira.next());
        }
    }

    public static void main(String[] args) {
        configureInjection();

        Usuario user = new Usuario("154.796.276-35", "email.muitolegal@gmail.com", "12345");

        String cpf = cadastroUseCase.cadastrar(user);
        System.out.printf("Usuário de cpf %s cadastrado com sucesso!\n\n", cpf);

        System.out.println("Testando login...");
        Usuario logado = loginUseCase.login("email.muitolegal@gmail.com", "12345");

        if(logado != null) {
            System.out.println(logado);
            System.out.println("Logado!");

            Grupo grupo = new Grupo("grupo muito legal");
            //Grupo grupo2 = new Grupo("grupo ainda mais legal");
            criarGrupoUseCase.include(logado, grupo);
            //criarGrupoUseCase.include(logado, grupo2);
            //System.out.println(criarGrupoUseCase.include(logado, grupo));

            Ativo ativoLegal = new Acao(10, 5, "USA", "tecnologia");
            incluirAcaoUseCase.include((Acao) ativoLegal);

            Ativo ativoLegal2 = new Acao(20, 10, "JPY", "tecnologia2");
            incluirAcaoUseCase.include((Acao) ativoLegal2);

            Ativo ativoLegal3 = new Acao(4, 2, "BRL", "tecnologia3");
            incluirAcaoUseCase.include((Acao) ativoLegal3);

            compraAtivosUseCase.compraAtivo(grupo, ativoLegal);

            ativoLegal.setValorAtual(20);
            alterarAcaoUseCase.update((Acao) ativoLegal);

            vendaAtivosUseCase.vendaAtivo(grupo, ativoLegal);

            printCarteira(logado.getIteratorCarteira());
        } else {
            System.out.println("Login falhou :(");
        }
    }
}
