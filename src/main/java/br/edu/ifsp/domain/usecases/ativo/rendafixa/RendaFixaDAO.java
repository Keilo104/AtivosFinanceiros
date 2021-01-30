package br.edu.ifsp.domain.usecases.ativo.rendafixa;

import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.usecases.utils.DAO;

public interface RendaFixaDAO extends DAO<RendaFixa, Integer> {
    Integer create(RendaFixa rendaFixa, Integer idAtivo);
}
