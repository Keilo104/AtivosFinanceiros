package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.usuario.Token;
import br.edu.ifsp.domain.usecases.usuario.TokenDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryTokenDAO implements TokenDAO {
    @Override
    public Integer create(Token type) {
        return null;
    }

    @Override
    public Optional<Token> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Token> findAll() {
        return null;
    }

    @Override
    public boolean update(Token type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Token type) {
        return false;
    }
}
