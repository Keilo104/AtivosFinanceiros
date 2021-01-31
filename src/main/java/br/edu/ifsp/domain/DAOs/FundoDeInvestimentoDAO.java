package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;

import java.util.List;

public interface FundoDeInvestimentoDAO extends DAO<FundoDeInvestimento, Integer> {
    List<FundoDeInvestimento> findAllByGrupo(int idGrupo);
}
