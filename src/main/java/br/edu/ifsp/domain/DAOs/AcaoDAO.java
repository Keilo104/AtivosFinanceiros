package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.Acao;

public interface AcaoDAO extends DAO<Acao, Integer> {
    Integer create(Acao acao, Integer idAtivo);
}
