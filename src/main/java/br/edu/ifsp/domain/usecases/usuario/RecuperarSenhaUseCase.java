package br.edu.ifsp.domain.usecases.usuario;

public class RecuperarSenhaUseCase {
    private UsuarioDAO usuarioDAO;
    private TokenDAO tokenDAO;

    public RecuperarSenhaUseCase( UsuarioDAO usuarioDAO, TokenDAO tokenDAO) {
        this.usuarioDAO = usuarioDAO;
        this.tokenDAO = tokenDAO;
    }
}
