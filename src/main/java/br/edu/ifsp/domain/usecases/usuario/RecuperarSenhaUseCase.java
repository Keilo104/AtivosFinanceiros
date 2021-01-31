package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.DAOs.TokenDAO;
import br.edu.ifsp.domain.DAOs.UsuarioDAO;

public class RecuperarSenhaUseCase {
    private UsuarioDAO usuarioDAO;
    private TokenDAO tokenDAO;

    public RecuperarSenhaUseCase( UsuarioDAO usuarioDAO, TokenDAO tokenDAO) {
        this.usuarioDAO = usuarioDAO;
        this.tokenDAO = tokenDAO;
    }
}
