package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.Log;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.usecases.log.logativo.LogAtivoDAO;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.*;

public class InMemoryLogAtivoDAO implements LogAtivoDAO {
    private static final Map<Pair<LocalDate, Ativo>, LogAtivo> db = new LinkedHashMap<>();

    @Override
    public Pair<LocalDate, Ativo> create(LogAtivo logAtivo) {
        db.put(logAtivo.generateKey(), logAtivo);

        return logAtivo.generateKey();
    }

    @Override
    public Optional<LogAtivo> findOne(Pair<LocalDate, Ativo> key) {
        if(db.containsKey(key)){
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<LogAtivo> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(LogAtivo logAtivo) {
        Pair<LocalDate, Ativo> pair = logAtivo.generateKey();
        if(db.containsKey(pair)) {
            db.replace(pair, logAtivo);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Pair<LocalDate, Ativo> key) {
        if(db.containsKey(key)) {
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(LogAtivo logAtivo) {
        return deleteByKey(logAtivo.generateKey());
    }
}
