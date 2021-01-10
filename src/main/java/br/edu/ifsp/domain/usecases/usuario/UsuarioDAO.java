package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.utils.DAO;

public interface UsuarioDAO extends DAO<Usuario, String> {
    boolean checkLogin( Usuario usuario );
}
