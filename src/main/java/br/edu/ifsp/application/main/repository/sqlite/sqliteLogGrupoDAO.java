package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.log.LogGrupo;
import br.edu.ifsp.domain.usecases.log.loggrupo.LogGrupoDAO;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class sqliteLogGrupoDAO implements LogGrupoDAO {//TODO
    @Override
    public Pair<LocalDate, Grupo> create(LogGrupo type) {
        return null;
    }

    @Override
    public Optional<LogGrupo> findOne(Pair<LocalDate, Grupo> key) {
        return Optional.empty();
    }

    @Override
    public List<LogGrupo> findAll() {
        return null;
    }

    @Override
    public boolean update(LogGrupo type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Pair<LocalDate, Grupo> key) {
        return false;
    }

    @Override
    public boolean delete(LogGrupo type) {
        return false;
    }
}
