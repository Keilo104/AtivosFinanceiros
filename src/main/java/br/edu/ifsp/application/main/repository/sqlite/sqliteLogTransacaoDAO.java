package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.usecases.log.logtransacao.LogTransacaoDAO;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class sqliteLogTransacaoDAO implements LogTransacaoDAO {//TODO
    @Override
    public Pair<LocalDate, Ativo> create(LogTransacaoAtivo type) {
        return null;
    }

    @Override
    public Optional<LogTransacaoAtivo> findOne(Pair<LocalDate, Ativo> key) {
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
    public boolean deleteByKey(Pair<LocalDate, Ativo> key) {
        return false;
    }

    @Override
    public boolean delete(LogTransacaoAtivo type) {
        return false;
    }
}
