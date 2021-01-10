package br.edu.ifsp.domain.usecases.usuario;

public class RecuperarSenhaUseCase {
    private UsuarioDAO usuarioDAO;

    public RecuperarSenhaUseCase( UsuarioDAO usuarioDAO ) {
        this.usuarioDAO = usuarioDAO;
    }
}
