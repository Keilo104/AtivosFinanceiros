package br.edu.ifsp.domain.usecases.log.logtransacao;

import br.edu.ifsp.domain.DAOs.LogTransacaoDAO;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;
import javafx.util.Pair;

import java.time.LocalDateTime;

public class SalvarHistoricoTransacaoUseCase {
    private LogTransacaoDAO logTransacaoDAO;

    public SalvarHistoricoTransacaoUseCase(LogTransacaoDAO logTransacaoDAO) {
        this.logTransacaoDAO = logTransacaoDAO;
    }

    public Pair<LocalDateTime, Ativo> salvarHistorico(Grupo grupo, LogTransacaoAtivo logTransacaoAtivo) {
        Validator<LogTransacaoAtivo> validator = new LogTransacaoInputValidator();
        Notification notif = validator.validate(logTransacaoAtivo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        Pair<LocalDateTime, Ativo> pair = this.logTransacaoDAO.create(logTransacaoAtivo);

        grupo.addLog(logTransacaoAtivo);

        return pair;
    }
}
