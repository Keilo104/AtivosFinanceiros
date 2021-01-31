package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;

import java.util.List;

public interface AcaoDAO extends DAO<Acao, Integer> {
    List<Ativo> findAllByGrupo(int idGrupo);
}
