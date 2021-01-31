package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.RendaFixa;

public interface RendaFixaDAO extends DAO<RendaFixa, Integer> {
    Integer create(RendaFixa rendaFixa, Integer idAtivo);
}
