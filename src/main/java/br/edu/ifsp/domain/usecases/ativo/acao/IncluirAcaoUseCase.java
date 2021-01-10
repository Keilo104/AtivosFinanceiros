package br.edu.ifsp.domain.usecases.ativo.acao;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogAtivoEnum;
import br.edu.ifsp.domain.usecases.log.logativo.LogAtivoDAO;
import br.edu.ifsp.domain.usecases.log.logativo.SalvarHistoricoAtivoUseCase;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class IncluirAcaoUseCase {
    private AcaoDAO acaoDAO;
    private LogAtivoDAO logAtivoDAO;

    public IncluirAcaoUseCase(AcaoDAO acaoDAO, LogAtivoDAO logAtivoDAO) {
        this.acaoDAO = acaoDAO;
        this.logAtivoDAO = logAtivoDAO;
    }

    public int include(Acao acao) {
        Validator<Acao> validator = new AcaoInputValidator();
        Notification notif = validator.validate(acao);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        Integer id = this.acaoDAO.create(acao);

        SalvarHistoricoAtivoUseCase salvarHistoricoAtivoUseCase = new SalvarHistoricoAtivoUseCase(logAtivoDAO);
        LogAtivo logAtivo = new LogAtivo(acao, LogAtivoEnum.INCLUSAO);
        salvarHistoricoAtivoUseCase.salvarHistorico(logAtivo);

        return id;
    }
}
