package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.usecases.log.logtransacao.LogTransacaoDAO;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.*;

public class InMemoryLogTransacaoDAO implements LogTransacaoDAO {
    private static final Map<Pair<LocalDate, Ativo>, LogTransacaoAtivo> db = new LinkedHashMap<>();

    @Override
    public Pair<LocalDate, Ativo> create(LogTransacaoAtivo logTransacaoAtivo) {
        db.put(logTransacaoAtivo.generateKey(), logTransacaoAtivo);
        return logTransacaoAtivo.generateKey();
    }

    @Override
    public Optional<LogTransacaoAtivo> findOne(Pair<LocalDate, Ativo> key) {
        if(db.containsKey(key)){
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<LogTransacaoAtivo> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(LogTransacaoAtivo logTransacaoAtivo) {
        Pair<LocalDate, Ativo> pair = logTransacaoAtivo.generateKey();
        if(db.containsKey(pair)) {
            db.replace(pair, logTransacaoAtivo);
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
    public boolean delete(LogTransacaoAtivo logTransacaoAtivo) {
        return deleteByKey(logTransacaoAtivo.generateKey());
    }
}
