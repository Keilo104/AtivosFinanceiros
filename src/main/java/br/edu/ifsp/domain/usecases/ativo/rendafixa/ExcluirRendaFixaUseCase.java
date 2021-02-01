package br.edu.ifsp.domain.usecases.ativo.rendafixa;

import br.edu.ifsp.domain.DAOs.GrupoDAO;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.DAOs.RendaFixaDAO;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogAtivoEnum;
import br.edu.ifsp.domain.usecases.log.logativo.SalvarHistoricoAtivoUseCase;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class ExcluirRendaFixaUseCase {
    private RendaFixaDAO rendaFixaDAO;
    private LogAtivoDAO logAtivoDAO;
    private GrupoDAO grupoDAO;

    public ExcluirRendaFixaUseCase( RendaFixaDAO rendaFixaDAO, LogAtivoDAO logAtivoDAO, GrupoDAO grupoDAO ) {
        this.rendaFixaDAO = rendaFixaDAO;
        this.logAtivoDAO = logAtivoDAO;
        this.grupoDAO = grupoDAO;
    }

    public boolean delete( RendaFixa rendaFixa ) {
        Validator<RendaFixa> validator = new RendaFixaInputValidator();
        Notification notif = validator.validate( rendaFixa );

        if ( notif.hasErrors() ) {
            throw new IllegalArgumentException( notif.errorMessage() );
        }

        if ( this.grupoDAO.findOneByAtivo( rendaFixa ).isPresent() ) {
            throw new IllegalArgumentException( "Cant delete ativo thats in a grupo" );
        }

        boolean flag = this.rendaFixaDAO.delete( rendaFixa );

        SalvarHistoricoAtivoUseCase salvarHistoricoAtivoUseCase = new SalvarHistoricoAtivoUseCase( logAtivoDAO );
        LogAtivo logAtivo = new LogAtivo( rendaFixa, LogAtivoEnum.REMOCAO );
        salvarHistoricoAtivoUseCase.salvarHistorico( logAtivo );

        return flag;
    }
}
