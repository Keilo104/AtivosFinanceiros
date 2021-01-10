package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class LoginUseCase {
    private UsuarioDAO usuarioDAO;

    public LoginUseCase( UsuarioDAO usuarioDAO ) {
        this.usuarioDAO = usuarioDAO;
    }

    public int login( Usuario usuario ) {
        Validator<Usuario> validator = new UsuarioInputValidator();
        Notification notif = validator.validate( usuario );

        if ( notif.hasErrors() ) {
            throw new IllegalArgumentException( notif.errorMessage() );
        }

        return this.usuarioDAO.create( usuario );
    }
}
