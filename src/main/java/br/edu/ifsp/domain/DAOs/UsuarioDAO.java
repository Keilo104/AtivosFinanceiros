package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.usuario.Usuario;

public interface UsuarioDAO extends DAO<Usuario, String> {
    Usuario checkLogin( String email, String senha );
}
