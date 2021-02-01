package br.edu.ifsp.domain.usecases.log.logativo;

import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class LogAtivoInputValidator extends Validator<LogAtivo> {
    @Override
    public Notification validate( LogAtivo logAtivo ) {
        Notification notification = new Notification();

        if ( logAtivo == null ) {
            notification.addError( "Acao is null" );
            return notification;
        }

        if ( logAtivo.getData() == null ) {
            notification.addError( "Data cannot be null" );
        }
        if ( logAtivo.getAtivo() == null ) {
            notification.addError( "Ativo cannot be null" );
        }
        if ( logAtivo.getTipo() == null ) {
            notification.addError( "Tipo cannot be null" );
        }

        return notification;
    }
}
