package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.DAOs.UsuarioDAO;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class LoginUseCase {
    private UsuarioDAO usuarioDAO;

    public LoginUseCase( UsuarioDAO usuarioDAO ) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario login( String email, String senha ) {
        Validator<Usuario> validator = new UsuarioInputValidator();
        Notification notif = ((UsuarioInputValidator) validator).validateLogin( email, senha );

        if ( notif.hasErrors() ) {
            throw new IllegalArgumentException( notif.errorMessage() );
        }

        return this.usuarioDAO.checkLogin( email, senha );
    }
}
