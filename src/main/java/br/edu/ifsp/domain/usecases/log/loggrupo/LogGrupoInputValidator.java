package br.edu.ifsp.domain.usecases.log.loggrupo;

import br.edu.ifsp.domain.entities.log.LogGrupo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class LogGrupoInputValidator extends Validator<LogGrupo> {
    @Override
    public Notification validate(LogGrupo logGrupo) {
        Notification notification = new Notification();

        if (logGrupo == null) {
            notification.addError("Acao is null");
            return notification;
        }

        if (logGrupo.getData() == null) {
            notification.addError("Data cannot be null");
        }
        if (logGrupo.getGrupo() == null) {
            notification.addError("Grupo cannot be null");
        }

        return notification;
    }
}
