package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.grupo.GrupoDAO;

import java.util.List;
import java.util.Optional;

public class sqliteGrupoDAO implements GrupoDAO {
    @Override
    public Optional<Grupo> findOneByNome(String nome) {
        return Optional.empty();
    }

    @Override
    public Optional<Grupo> findOneByAtivo(Ativo ativo) {
        return Optional.empty();
    }

    @Override
    public Integer create(Grupo type) {
        return null;
    }

    @Override
    public Optional<Grupo> findOne(Integer key) {
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
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Grupo type) {
        return false;
    }
}
