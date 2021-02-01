package br.edu.ifsp.application.main.repository.inMemory;

import br.edu.ifsp.domain.DAOs.RelatorioDAO;
import br.edu.ifsp.domain.entities.relatorio.Relatorio;

import java.util.*;

public class InMemoryRelatorioDAO implements RelatorioDAO {
    private static final Map<Integer, Relatorio> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create( Relatorio relatorio ) {
        idCounter++;
        relatorio.setId( idCounter );
        db.put( idCounter, relatorio );
        return idCounter;
    }

    @Override
    public Optional<Relatorio> findOne( Integer key ) {
        if ( db.containsKey( key ) ) {
            return Optional.of( db.get( key ) );
        }
        return Optional.empty();
    }

    @Override
    public List<Relatorio> findAll() {
        return new ArrayList<>( db.values() );
    }

    @Override
    public boolean update( Relatorio relatorio ) {
        Integer id = relatorio.getId();
        if ( db.containsKey( id ) ) {
            db.replace( id, relatorio );
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey( Integer key ) {
        if ( db.containsKey( key ) ) {
            db.remove( key );
            return true;
        }
        return false;
    }

    @Override
    public boolean delete( Relatorio relatorio ) {
        return deleteByKey( relatorio.getId() );
    }
}
