package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;

import java.util.List;

public interface RendaFixaDAO extends DAO<RendaFixa, Integer> {
    List<Ativo> findAllByGrupo(int idGrupo);
}
