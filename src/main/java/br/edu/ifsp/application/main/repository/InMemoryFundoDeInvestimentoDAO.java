package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.FundoDeInvestimentoDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryFundoDeInvestimentoDAO implements FundoDeInvestimentoDAO {
    @Override
    public Integer create(FundoDeInvestimento type) {
        return null;
    }

    @Override
    public Optional<FundoDeInvestimento> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<FundoDeInvestimento> findAll() {
        return null;
    }

    @Override
    public boolean update(FundoDeInvestimento type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(FundoDeInvestimento type) {
        return false;
    }
}
