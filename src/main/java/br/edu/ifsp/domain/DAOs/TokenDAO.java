package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.usuario.Token;

public interface TokenDAO extends DAO<Token, String> {
    boolean findIfExists( String token );
}
