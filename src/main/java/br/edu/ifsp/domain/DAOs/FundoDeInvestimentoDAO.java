package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.util.List;

public interface FundoDeInvestimentoDAO extends DAO<FundoDeInvestimento, Integer> {
    List<Ativo> findAllByGrupo( Grupo grupo );
}
