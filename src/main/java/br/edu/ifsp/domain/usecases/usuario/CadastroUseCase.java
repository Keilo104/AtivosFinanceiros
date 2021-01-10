package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CadastroUseCase {
    private UsuarioDAO usuarioDAO;

    public CadastroUseCase( UsuarioDAO usuarioDAO ) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean cadastrar( Usuario usuario ) {
        Validator<Usuario> validator = new UsuarioInputValidator();
        Notification notif = validator.validate( usuario );

        if ( notif.hasErrors() ) {
            throw new IllegalArgumentException( notif.errorMessage() );
        }

        return this.usuarioDAO.checkLogin( usuario );
    }
}