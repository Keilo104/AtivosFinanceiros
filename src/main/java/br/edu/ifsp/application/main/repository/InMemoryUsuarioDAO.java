package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryUsuarioDAO implements UsuarioDAO{
    @Override
    public int checkLogin(Usuario usuario) {
        return 0;
    }

    @Override
    public Integer create(Usuario type) {
        return null;
    }

    @Override
    public Optional<Usuario> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public boolean update(Usuario type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Usuario type) {
        return false;
    }
}
