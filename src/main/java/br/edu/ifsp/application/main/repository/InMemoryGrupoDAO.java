package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.grupo.GrupoDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryGrupoDAO implements GrupoDAO {
    @Override
    public String create(Grupo type) {
        return null;
    }

    @Override
    public Optional<Grupo> findOne(String key) {
        return Optional.empty();
    }

    @Override
    public List<Grupo> findAll() {
        return null;
    }

    @Override
    public boolean update(Grupo type) {
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        return false;
    }

    @Override
    public boolean delete(Grupo type) {
        return false;
    }
}
