package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.Acao;

import java.util.List;

public interface APIDAO {
    float getNewPrice( String codigo );

    Acao getOne( String codigo );

    List<Acao> search( String keyword );
}
