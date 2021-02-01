package br.edu.ifsp.application.main.repository.inMemory;

import br.edu.ifsp.domain.DAOs.LogTransacaoDAO;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.*;

public class InMemoryLogTransacaoDAO implements LogTransacaoDAO {
    private static final Map<Pair<LocalDateTime, Ativo>, LogTransacaoAtivo> db = new LinkedHashMap<>();

    @Override
    public Pair<LocalDateTime, Ativo> create( LogTransacaoAtivo logTransacaoAtivo ) {
        db.put( logTransacaoAtivo.generateKey(), logTransacaoAtivo );
        return logTransacaoAtivo.generateKey();
    }

    @Override
    public Optional<LogTransacaoAtivo> findOne( Pair<LocalDateTime, Ativo> key ) {
        if ( db.containsKey( key ) ) {
            return Optional.of( db.get( key ) );
        }
        return Optional.empty();
    }

    @Override
    public List<LogTransacaoAtivo> findAll() {
        return new ArrayList<>( db.values() );
    }

    @Override
    public boolean update( LogTransacaoAtivo logTransacaoAtivo ) {
        Pair<LocalDateTime, Ativo> pair = logTransacaoAtivo.generateKey();
        if ( db.containsKey( pair ) ) {
            db.replace( pair, logTransacaoAtivo );
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey( Pair<LocalDateTime, Ativo> key ) {
        if ( db.containsKey( key ) ) {
            db.remove( key );
            return true;
        }
        return false;
    }

    @Override
    public boolean delete( LogTransacaoAtivo logTransacaoAtivo ) {
        return deleteByKey( logTransacaoAtivo.generateKey() );
    }
}
