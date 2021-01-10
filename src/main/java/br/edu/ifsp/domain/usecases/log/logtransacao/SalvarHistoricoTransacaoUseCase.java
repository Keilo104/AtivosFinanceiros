package br.edu.ifsp.domain.usecases.log.logtransacao;

import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class SalvarHistoricoTransacaoUseCase {
    private LogTransacaoDAO logTransacaoDAO;

    public SalvarHistoricoTransacaoUseCase(LogTransacaoDAO logTransacaoDAO) {
        this.logTransacaoDAO = logTransacaoDAO;
    }

    public int salvarHistorico(LogTransacaoAtivo logTransacaoAtivo) {
        Validator<LogTransacaoAtivo> validator = new LogTransacaoInputValidator();
        Notification notif = validator.validate(logTransacaoAtivo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        return this.logTransacaoDAO.create(logTransacaoAtivo);
    }
}
