package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.application.main.repository.inMemory.InMemoryAtivosDAO;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.usecases.ativo.AtivosDAO;

import java.util.List;
import java.util.Optional;

public class sqliteAtivosDAO implements AtivosDAO {
    @Override
    public Integer create(Ativo type) {
        return null;
    }

    @Override
    public Optional<Ativo> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Ativo> findAll() {
        return null;
    }

    @Override
    public boolean update(Ativo type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Ativo type) {
        return false;
    }
}
