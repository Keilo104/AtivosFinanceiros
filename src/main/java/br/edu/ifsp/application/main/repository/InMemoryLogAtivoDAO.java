package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.usecases.log.logativo.LogAtivoDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryLogAtivoDAO implements LogAtivoDAO {
    @Override
    public Integer create(LogAtivo type) {
        return null;
    }

    @Override
    public Optional<LogAtivo> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<LogAtivo> findAll() {
        return null;
    }

    @Override
    public boolean update(LogAtivo type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(LogAtivo type) {
        return false;
    }
}
