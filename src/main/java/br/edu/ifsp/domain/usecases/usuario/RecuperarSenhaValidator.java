package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.usuario.Token;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class RecuperarSenhaValidator extends Validator<Token> {

    @Override
    public Notification validate( Token token ) {
        Notification notification = new Notification();

        if ( token == null ) {
            notification.addError( "Token is null" );
            return notification;
        }

        if ( token.getToken() == null || token.getToken().isEmpty() || token.getToken().isBlank() ) {
            notification.addError( "Invalid Token" );
        }

        return notification;
    }
}
