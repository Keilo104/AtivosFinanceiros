package br.edu.ifsp.domain.usecases.ativo.acao;

import br.edu.ifsp.domain.DAOs.AcaoDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogAtivoEnum;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.usecases.log.logativo.SalvarHistoricoAtivoUseCase;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarAcaoUseCase {
    private AcaoDAO acaoDAO;
    private LogAtivoDAO logAtivoDAO;

    public AlterarAcaoUseCase(AcaoDAO acaoDAO, LogAtivoDAO logAtivoDAO) {
        this.acaoDAO = acaoDAO;
        this.logAtivoDAO = logAtivoDAO;
    }

    public boolean update(Acao acao) {
        Validator<Acao> validator = new AcaoInputValidator();
        Notification notif = validator.validate(acao);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        boolean flag = this.acaoDAO.update(acao);

        SalvarHistoricoAtivoUseCase salvarHistoricoAtivoUseCase = new SalvarHistoricoAtivoUseCase(logAtivoDAO);
        LogAtivo logAtivo = new LogAtivo(acao, LogAtivoEnum.ALTERACAO);
        salvarHistoricoAtivoUseCase.salvarHistorico(logAtivo);

        return flag;
    }

}
