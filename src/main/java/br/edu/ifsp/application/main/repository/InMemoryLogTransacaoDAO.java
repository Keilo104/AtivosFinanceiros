package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.usecases.log.logtransacao.LogTransacaoDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryLogTransacaoDAO implements LogTransacaoDAO {
    @Override
    public Integer create(LogTransacaoAtivo type) {
        return null;
    }

    @Override
    public Optional<LogTransacaoAtivo> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<LogTransacaoAtivo> findAll() {
        return null;
    }

    @Override
    public boolean update(LogTransacaoAtivo type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(LogTransacaoAtivo type) {
        return false;
    }
}
