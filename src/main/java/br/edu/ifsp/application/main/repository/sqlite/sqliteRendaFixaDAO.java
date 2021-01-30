package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.RendaFixaDAO;

import java.util.List;
import java.util.Optional;

public class sqliteRendaFixaDAO implements RendaFixaDAO {
    @Override
    public Integer create(RendaFixa type) {
        return null;
    }

    @Override
    public Optional<RendaFixa> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<RendaFixa> findAll() {
        return null;
    }

    @Override
    public boolean update(RendaFixa type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(RendaFixa type) {
        return false;
    }
}
