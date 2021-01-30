package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.usecases.log.logativo.LogAtivoDAO;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class sqliteLogAtivoDAO implements LogAtivoDAO {
    @Override
    public Pair<LocalDate, Ativo> create(LogAtivo type) {
        return null;
    }

    @Override
    public Optional<LogAtivo> findOne(Pair<LocalDate, Ativo> key) {
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
    public boolean deleteByKey(Pair<LocalDate, Ativo> key) {
        return false;
    }

    @Override
    public boolean delete(LogAtivo type) {
        return false;
    }
}
