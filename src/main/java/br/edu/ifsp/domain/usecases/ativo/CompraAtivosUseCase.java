package br.edu.ifsp.domain.usecases.ativo;

import br.edu.ifsp.domain.DAOs.AtivosDAO;
import br.edu.ifsp.domain.DAOs.GrupoDAO;
import br.edu.ifsp.domain.DAOs.LogGrupoDAO;
import br.edu.ifsp.domain.DAOs.LogTransacaoDAO;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.log.LogGrupo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivoEnum;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.grupo.GrupoInputValidator;
import br.edu.ifsp.domain.usecases.log.loggrupo.SalvarHistoricoGrupoUseCase;
import br.edu.ifsp.domain.usecases.log.logtransacao.SalvarHistoricoTransacaoUseCase;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CompraAtivosUseCase {
    private AtivosDAO ativosDAO;
    private GrupoDAO grupoDAO;
    private LogTransacaoDAO logTransacaoDAO;
    private LogGrupoDAO logGrupoDAO;

    public CompraAtivosUseCase( AtivosDAO ativoDAO, GrupoDAO grupoDAO, LogTransacaoDAO logTransacaoDAO, LogGrupoDAO logGrupoDAO ) {
        this.ativosDAO = ativoDAO;
        this.grupoDAO = grupoDAO;
        this.logTransacaoDAO = logTransacaoDAO;
        this.logGrupoDAO = logGrupoDAO;
    }

    public boolean compraAtivo( Usuario usuario, Grupo grupo, Ativo ativo ) {
        Validator<Ativo> validatorAtivo = new AtivosValidator();
        Validator<Grupo> validatorGrupo = new GrupoInputValidator();

        Notification notif = validatorAtivo.validate( ativo );
        notif.addErrors( validatorGrupo.validate( grupo ) );

        if ( notif.hasErrors() ) {
            throw new IllegalArgumentException( notif.errorMessage() );
        }

        ativo.setIDGrupo( grupo.getId() );

        if ( ativosDAO.update( ativo ) ) {
            float lucroAnterior = grupo.getTotalLucrado();
            grupo.addAtivo( ativo );
            //System.out.println(grupo);
            boolean flag = grupoDAO.update( grupo );
            float lucroAtual = grupo.getTotalLucrado();

            SalvarHistoricoTransacaoUseCase salvarHistoricoAtivoUseCase = new SalvarHistoricoTransacaoUseCase( logTransacaoDAO );
            LogTransacaoAtivo logTransacaoAtivo = new LogTransacaoAtivo( ativo, LogTransacaoAtivoEnum.COMPRA, ativo.getValorUnitarioAtual(), ativo.getQuantidade() );
            salvarHistoricoAtivoUseCase.salvarHistorico( grupo, logTransacaoAtivo );

            SalvarHistoricoGrupoUseCase salvarHistoricoGrupoUseCase = new SalvarHistoricoGrupoUseCase( logGrupoDAO );
            LogGrupo logGrupo = new LogGrupo( grupo, lucroAtual, lucroAtual - lucroAnterior );
            salvarHistoricoGrupoUseCase.salvarHistorico( usuario, logGrupo );

            return flag;
        }
        return false;
    }
}
