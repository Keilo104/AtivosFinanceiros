package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.util.List;

public interface AcaoDAO extends DAO<Acao, Integer> {
    List<Ativo> findAllByGrupo(Grupo grupo);

    List<Acao> findAllWithoutGroup();
}
