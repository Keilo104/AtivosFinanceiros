package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.util.List;

public interface RendaFixaDAO extends DAO<RendaFixa, Integer> {
    List<Ativo> findAllByGrupo( Grupo grupo );
}
