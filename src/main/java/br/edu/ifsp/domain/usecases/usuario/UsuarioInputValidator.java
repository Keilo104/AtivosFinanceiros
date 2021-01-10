package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class UsuarioInputValidator extends Validator<Usuario> {

    @Override
    public Notification validate( Usuario usuario ) {
        Notification notification = new Notification();

        if ( usuario == null ) {
            notification.addError( "Usuario is null" );
            return notification;
        }

        if ( usuario.getEmail() == null ) {
            notification.addError( "Email cannot be null" );
        }
        if ( usuario.getSenha() == null ) {
            notification.addError( "Senha cannot be null" );
        }

        return notification;
    }
}