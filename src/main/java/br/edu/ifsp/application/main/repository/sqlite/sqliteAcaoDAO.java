package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.usecases.ativo.acao.AcaoDAO;

import java.util.List;
import java.util.Optional;

public class sqliteAcaoDAO implements AcaoDAO {
    @Override
    public Integer create(Acao type) {
        return null;
    }

    @Override
    public Optional<Acao> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Acao> findAll() {
        return null;
    }

    @Override
    public boolean update(Acao type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Acao type) {
        return false;
    }
}
