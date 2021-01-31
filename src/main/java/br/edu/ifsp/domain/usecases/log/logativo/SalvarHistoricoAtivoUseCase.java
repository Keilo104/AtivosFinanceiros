package br.edu.ifsp.domain.usecases.log.logativo;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;
import javafx.util.Pair;

import java.time.LocalDateTime;

public class SalvarHistoricoAtivoUseCase {
    private LogAtivoDAO logAtivoDAO;

    public SalvarHistoricoAtivoUseCase(LogAtivoDAO logAtivoDAO) {
        this.logAtivoDAO = logAtivoDAO;
    }

    public Pair<LocalDateTime, Ativo> salvarHistorico(LogAtivo logAtivo) {
        Validator<LogAtivo> validator = new LogAtivoInputValidator();
        Notification notif = validator.validate(logAtivo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        return this.logAtivoDAO.create(logAtivo);
    }
}
