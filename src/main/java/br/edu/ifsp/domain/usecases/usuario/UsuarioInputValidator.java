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

        if ( nullOrEmpty(usuario.getCpf()) ) {
            notification.addError( "CPF cannot be null or empty" );
        }
        if ( nullOrEmpty(usuario.getEmail()) ) {
            notification.addError( "Email cannot be  or empty" );
        }
        if ( nullOrEmpty(usuario.getSenha()) ) {
            notification.addError( "Senha cannot be null or empty" );
        }

        return notification;
    }
}