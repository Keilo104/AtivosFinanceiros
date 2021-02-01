package br.edu.ifsp.application.main;

import br.edu.ifsp.application.main.repository.sqlite.sqliteTokenDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteUsuarioDAO;
import br.edu.ifsp.domain.DAOs.TokenDAO;
import br.edu.ifsp.domain.DAOs.UsuarioDAO;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.ativo.CompraAtivosUseCase;
import br.edu.ifsp.domain.usecases.ativo.VendaAtivosUseCase;
import br.edu.ifsp.domain.usecases.ativo.acao.ExcluirAcaoUseCase;
import br.edu.ifsp.domain.usecases.ativo.acao.IncluirAcaoUseCase;
import br.edu.ifsp.domain.usecases.ativo.acao.UpdateAPIAcaoUseCase;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.AlterarFundoDeInvestimentoUseCase;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.ExcluirFundoDeInvestimentoUseCase;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.IncluirFundoDeInvestimentoUseCase;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.AlterarRendaFixaUseCase;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.ExcluirRendaFixaUseCase;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.IncluirRendaFixaUseCase;
import br.edu.ifsp.domain.usecases.grupo.AtualizarGrupoUseCase;
import br.edu.ifsp.domain.usecases.grupo.CriarGrupoUseCase;
import br.edu.ifsp.domain.usecases.grupo.ExcluirGrupoUseCase;
import br.edu.ifsp.domain.usecases.relatorio.GerarRelatorioCategoriaUseCase;
import br.edu.ifsp.domain.usecases.relatorio.GerarRelatorioPeriodoUseCase;
import br.edu.ifsp.domain.usecases.usuario.CadastroUseCase;
import br.edu.ifsp.domain.usecases.usuario.LoginUseCase;
import br.edu.ifsp.domain.usecases.usuario.RecuperarSenhaUseCase;

import java.util.Iterator;
import java.util.Map;

public class Main {
    //ativo
    private static CompraAtivosUseCase compraAtivosUseCase;
    private static VendaAtivosUseCase vendaAtivosUseCase;

    // acao
    private static UpdateAPIAcaoUseCase updateAPIAcaoUseCase;
    private static ExcluirAcaoUseCase excluirAcaoUseCase;
    private static IncluirAcaoUseCase incluirAcaoUseCase;

    //fundo de investimento
    private static AlterarFundoDeInvestimentoUseCase alterarFundoDeInvestimentoUseCase;
    private static ExcluirFundoDeInvestimentoUseCase excluirFundoDeInvestimentoUseCase;
    private static IncluirFundoDeInvestimentoUseCase incluirFundoDeInvestimentoUseCase;

    //renda fixa
    private static AlterarRendaFixaUseCase alterarRendaFixaUseCase;
    private static ExcluirRendaFixaUseCase excluirRendaFixaUseCase;
    private static IncluirRendaFixaUseCase incluirRendaFixaUseCase;

    //grupo
    private static AtualizarGrupoUseCase atualizarGrupoUseCase;
    private static CriarGrupoUseCase criarGrupoUseCase;
    private static ExcluirGrupoUseCase excluirGrupoUseCase;

    //log ativo
    //private static SalvarHistoricoAtivoUseCase salvarHistoricoAtivoUseCase;

    //log transacao
    //private static SalvarHistoricoTransacaoUseCase salvarHistoricoTransacaoUseCase;

    //relatorio
    private static GerarRelatorioCategoriaUseCase gerarRelatorioCategoriaUseCase;
    private static GerarRelatorioPeriodoUseCase gerarRelatorioPeriodoUseCase;

    //usuario
    private static CadastroUseCase cadastroUseCase;
    private static LoginUseCase loginUseCase;
    private static RecuperarSenhaUseCase recuperarSenhaUseCase;

    private static Map<Integer, Ativo> ativoDB;

    private static void printCarteira( Iterator<Grupo> carteira ) {
        while ( carteira.hasNext() ) {
            System.out.println( carteira.next() );
        }
    }

    private static void configureInjection() {
        TokenDAO tokenDAO = new sqliteTokenDAO();
        UsuarioDAO usuarioDAO = new sqliteUsuarioDAO();
        /*
        //DAOs
        ativoDB = new LinkedHashMap<>();
        AtivosDAO ativosDAO = new InMemoryAtivosDAO( ativoDB );
        AcaoDAO acaoDAO = new InMemoryAcaoDAO( ativoDB );
        FundoDeInvestimentoDAO fundoDeInvestimentoDAO = new InMemoryFundoDeInvestimentoDAO( ativoDB );
        RendaFixaDAO rendaFixaDAO = new InMemoryRendaFixaDAO( ativoDB );
        GrupoDAO grupoDAO = new InMemoryGrupoDAO();
        LogAtivoDAO logAtivoDAO = new InMemoryLogAtivoDAO();
        LogTransacaoDAO logTransacaoDAO = new InMemoryLogTransacaoDAO();
        RelatorioDAO relatorioDAO = new InMemoryRelatorioDAO();

        //ativo
        compraAtivosUseCase = new CompraAtivosUseCase( ativosDAO, grupoDAO, logTransacaoDAO );
        vendaAtivosUseCase = new VendaAtivosUseCase( ativosDAO, grupoDAO, logTransacaoDAO );
        //acao
        updateAPIAcaoUseCase = new UpdateAPIAcaoUseCase( acaoDAO, logAtivoDAO );
        excluirAcaoUseCase = new ExcluirAcaoUseCase( acaoDAO, logAtivoDAO, grupoDAO );
        incluirAcaoUseCase = new IncluirAcaoUseCase( acaoDAO, logAtivoDAO );
        //fundo de investimento
        alterarFundoDeInvestimentoUseCase = new AlterarFundoDeInvestimentoUseCase( fundoDeInvestimentoDAO, logAtivoDAO );
        excluirFundoDeInvestimentoUseCase = new ExcluirFundoDeInvestimentoUseCase( fundoDeInvestimentoDAO, logAtivoDAO, grupoDAO );
        incluirFundoDeInvestimentoUseCase = new IncluirFundoDeInvestimentoUseCase( fundoDeInvestimentoDAO, logAtivoDAO );
        //renda fixa
        alterarRendaFixaUseCase = new AlterarRendaFixaUseCase( rendaFixaDAO, logAtivoDAO );
        excluirRendaFixaUseCase = new ExcluirRendaFixaUseCase( rendaFixaDAO, logAtivoDAO, grupoDAO );
        incluirRendaFixaUseCase = new IncluirRendaFixaUseCase( rendaFixaDAO, logAtivoDAO );
        //grupo
        atualizarGrupoUseCase = new AtualizarGrupoUseCase( grupoDAO );
        criarGrupoUseCase = new CriarGrupoUseCase( grupoDAO );
        excluirGrupoUseCase = new ExcluirGrupoUseCase( grupoDAO );
        //log ativo
        //salvarHistoricoAtivoUseCase = new SalvarHistoricoAtivoUseCase(logAtivoDAO);
        //log transacao
        //salvarHistoricoTransacaoUseCase = new SalvarHistoricoTransacaoUseCase(logTransacaoDAO);
        //relatorio
        gerarRelatorioCategoriaUseCase = new GerarRelatorioCategoriaUseCase( relatorioDAO );
        gerarRelatorioPeriodoUseCase = new GerarRelatorioPeriodoUseCase( relatorioDAO );
        //usuario
        cadastroUseCase = new CadastroUseCase( usuarioDAO );
        loginUseCase = new LoginUseCase( usuarioDAO );*/
        recuperarSenhaUseCase = new RecuperarSenhaUseCase( usuarioDAO, tokenDAO );
    }

    public static void main( String[] args ) {

        configureInjection();/*
        Usuario user = new Usuario( "154.796.276-35", "email.muitolegal@gmail.com", "12345" );
        String cpf = cadastroUseCase.cadastrar( user );
        System.out.printf( "Usuário de cpf %s cadastrado com sucesso!\n\n", cpf );
        System.out.println( "Testando login..." );
        Usuario logado = loginUseCase.login( "email.muitolegal@gmail.com", "12345" );
        if ( logado != null ) {
            //System.out.println(logado);
            System.out.println( "Logado!" );
            Grupo grupo = new Grupo( "grupo muito legal", TipoGrupoEnum.ACAO );
            //Grupo grupo2 = new Grupo("grupo ainda mais legal");
            criarGrupoUseCase.include( logado, grupo );
            //criarGrupoUseCase.include(logado, grupo2);
            //System.out.println(criarGrupoUseCase.include(logado, grupo));
            Ativo ativoLegal = new Acao( 10, 5, "USA", "tecnologia" );
            incluirAcaoUseCase.include( ( Acao ) ativoLegal );
            Ativo ativoIlegal = new RendaFixa( 50, 7, "20%" );
            incluirRendaFixaUseCase.include( ( RendaFixa ) ativoIlegal );
            Ativo ativoLegal2 = new Acao( 20, 10, "JPY", "tecnologia2" );
            incluirAcaoUseCase.include( ( Acao ) ativoLegal2 );
            Ativo ativoLegal3 = new Acao( 4, 2, "BRL", "tecnologia3" );
            incluirAcaoUseCase.include( ( Acao ) ativoLegal3 );
            compraAtivosUseCase.compraAtivo(user, grupo, ativoLegal);
            compraAtivosUseCase.compraAtivo(user, grupo, ativoIlegal);
            ativoLegal.setValorUnitarioAtual( 20 );
            updateAPIAcaoUseCase.update( ( Acao ) ativoLegal );
            //printCarteira(logado.getIteratorCarteira());
            // System.out.println(excluirGrupoUseCase.delete(grupo));
            //printCarteira(logado.getIteratorCarteira());
            vendaAtivosUseCase.vendaAtivo(user, grupo, ativoLegal);
            //printCarteira(logado.getIteratorCarteira());
            excluirAcaoUseCase.delete( ( Acao ) ativoLegal );
            Relatorio relatorio = new Relatorio( CategoriaEnum.ACAO );
            gerarRelatorioCategoriaUseCase.gerar( relatorio );
            System.out.println( relatorio );
            //System.out.println(ativoDB);
            //printCarteira(logado.getIteratorCarteira());
            try {
                URL url = new URL( "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=MSFT&apikey=VPG6K3O2QHXZEWPG" );
                HttpURLConnection con = ( HttpURLConnection ) url.openConnection();
                con.setRequestMethod( "GET" );
                con.setRequestProperty( "Content-Type", "application/json; utf-8" );
                con.setRequestProperty( "Accept", "application/json" );
                try ( BufferedReader br = new BufferedReader(
                        new InputStreamReader( con.getInputStream(), "utf-8" ) ) ) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    List<String> lista = new ArrayList();
                    while ( ( responseLine = br.readLine() ) != null ) {
                        response.append( responseLine.trim() );
                    }
                    JSONObject jsonObj = new JSONObject( response.toString() );
                    System.out.println( jsonObj );
                    System.out.println( response.toString() );
                    float price = Float.parseFloat( jsonObj.getJSONObject( "Global Quote" ).getString( "05. price" ) );
                    System.out.println( "args = " + ( price + price ) );
                }
            } catch ( IOException | MalformedURLException | ProtocolException e ) {
                System.out.println( e );
            }
        } else {
            System.out.println( "Login falhou :(" );
        }
        */
        recuperarSenhaUseCase.enviarToken( "123" );
    }
}