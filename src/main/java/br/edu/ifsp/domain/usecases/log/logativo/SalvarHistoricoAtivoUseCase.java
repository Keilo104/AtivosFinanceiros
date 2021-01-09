package br.edu.ifsp.domain.usecases.log.logativo;

import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class SalvarHistoricoAtivoUseCase {
    private LogAtivoDAO logAtivoDAO;

    public SalvarHistoricoAtivoUseCase(LogAtivoDAO logAtivoDAO) {
        this.logAtivoDAO = logAtivoDAO;
    }

    public int salvarHistorico(LogAtivo logAtivo) {
        Validator<LogAtivo> validator = new LogAtivoInputValidator();
        Notification notif = validator.validate(logAtivo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        return this.logAtivoDAO.create(logAtivo);
    }
}
