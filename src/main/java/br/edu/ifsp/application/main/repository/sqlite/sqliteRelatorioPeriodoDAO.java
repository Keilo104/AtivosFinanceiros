package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import br.edu.ifsp.domain.usecases.relatorio.RelatorioPeriodoDAO;

import java.util.List;
import java.util.Optional;

public class sqliteRelatorioPeriodoDAO implements RelatorioPeriodoDAO {
    @Override
    public Integer create(Relatorio type) {
        return null;
    }

    @Override
    public Optional<Relatorio> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Relatorio> findAll() {
        return null;
    }

    @Override
    public boolean update(Relatorio type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Relatorio type) {
        return false;
    }
}
