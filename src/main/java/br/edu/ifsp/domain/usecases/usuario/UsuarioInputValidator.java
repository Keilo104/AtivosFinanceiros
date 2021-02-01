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

        if ( nullOrEmptyOrBlank( usuario.getCpf() ) ) {
            notification.addError( "CPF cannot be null or empty" );
        }

        if ( nullOrEmptyOrBlank( usuario.getNome() ) ) {
            notification.addError( "Nome cannot be null or empty" );
        }

        if ( nullOrEmptyOrBlank( usuario.getEmail() ) ) {
            notification.addError( "Email cannot be null or empty" );
        }
        if ( nullOrEmptyOrBlank( usuario.getSenha() ) ) {
            notification.addError( "Senha cannot be null or empty" );
        }

        return notification;
    }

    public Notification validateLogin( String email, String senha ) {
        Notification notification = new Notification();

        if ( nullOrEmptyOrBlank( email ) ) {
            notification.addError( "Email cannot be null or empty" );
        }
        if ( nullOrEmptyOrBlank( senha ) ) {
            notification.addError( "Senha cannot be null or empty" );
        }

        return notification;
    }
}