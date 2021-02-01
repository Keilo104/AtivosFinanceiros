package br.edu.ifsp.domain.usecases.ativo.rendafixa;

import br.edu.ifsp.domain.DAOs.AtivosDAO;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.DAOs.RendaFixaDAO;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogAtivoEnum;
import br.edu.ifsp.domain.usecases.log.logativo.SalvarHistoricoAtivoUseCase;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class IncluirRendaFixaUseCase {
    private RendaFixaDAO rendaFixaDAO;
    private LogAtivoDAO logAtivoDAO;
    private AtivosDAO ativosDAO;

    public IncluirRendaFixaUseCase( AtivosDAO ativosDAO, RendaFixaDAO rendaFixaDAO, LogAtivoDAO logAtivoDAO ) {
        this.rendaFixaDAO = rendaFixaDAO;
        this.logAtivoDAO = logAtivoDAO;
        this.ativosDAO = ativosDAO;
    }

    public int include( RendaFixa rendaFixa ) {
        Validator<RendaFixa> validator = new RendaFixaInputValidator();
        Notification notif = validator.validate( rendaFixa );

        if ( notif.hasErrors() ) {
            throw new IllegalArgumentException( notif.errorMessage() );
        }

        int id = this.rendaFixaDAO.create( rendaFixa );

        SalvarHistoricoAtivoUseCase salvarHistoricoAtivoUseCase = new SalvarHistoricoAtivoUseCase( logAtivoDAO );
        LogAtivo logAtivo = new LogAtivo( rendaFixa, LogAtivoEnum.INCLUSAO );
        salvarHistoricoAtivoUseCase.salvarHistorico( logAtivo );

        return id;
    }
}
