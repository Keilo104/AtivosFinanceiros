package br.edu.ifsp.domain.usecases.ativo.rendafixa;

import br.edu.ifsp.domain.DAOs.RendaFixaDAO;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogAtivoEnum;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.usecases.log.logativo.SalvarHistoricoAtivoUseCase;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarRendaFixaUseCase {
    private RendaFixaDAO rendaFixaDAO;
    private LogAtivoDAO logAtivoDAO;

    public AlterarRendaFixaUseCase(RendaFixaDAO rendaFixaDAO, LogAtivoDAO logAtivoDAO) {
        this.rendaFixaDAO = rendaFixaDAO;
        this.logAtivoDAO = logAtivoDAO;
    }

    public boolean include(RendaFixa rendaFixa) {
        Validator<RendaFixa> validator = new RendaFixaInputValidator();
        Notification notif = validator.validate(rendaFixa);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        boolean flag = this.rendaFixaDAO.update(rendaFixa);

        SalvarHistoricoAtivoUseCase salvarHistoricoAtivoUseCase = new SalvarHistoricoAtivoUseCase(logAtivoDAO);
        LogAtivo logAtivo = new LogAtivo(rendaFixa, LogAtivoEnum.ALTERACAO);
        salvarHistoricoAtivoUseCase.salvarHistorico(logAtivo);

        return flag;
    }
}
