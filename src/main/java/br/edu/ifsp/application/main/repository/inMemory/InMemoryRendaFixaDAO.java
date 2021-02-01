package br.edu.ifsp.application.main.repository.inMemory;

import br.edu.ifsp.domain.DAOs.RendaFixaDAO;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryRendaFixaDAO implements RendaFixaDAO {
    private static Map<Integer, Ativo> db;
    private static int idCounter;

    public InMemoryRendaFixaDAO( Map<Integer, Ativo> db ) {
        InMemoryRendaFixaDAO.db = db;
    }

    @Override
    public Integer create( RendaFixa rendaFixa ) {
        idCounter++;
        rendaFixa.setId( idCounter );
        db.put( idCounter, rendaFixa );
        return idCounter;
    }

    @Override
    public Optional<RendaFixa> findOne( Integer key ) {
        if ( db.containsKey( key ) ) {
            return Optional.of( ( RendaFixa ) db.get( key ) );
        }
        return Optional.empty();
    }

    @Override
    public List<RendaFixa> findAll() {
        ArrayList<RendaFixa> rendaFixaList = new ArrayList<>();

        for ( Ativo a : db.values() ) {
            if ( a instanceof RendaFixa )
                rendaFixaList.add( ( RendaFixa ) a );
        }

        return rendaFixaList;
    }

    @Override
    public boolean update( RendaFixa rendaFixa ) {
        Integer id = rendaFixa.getId();
        if ( db.containsKey( id ) ) {
            db.replace( id, rendaFixa );
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
    public boolean delete( RendaFixa rendaFixa ) {
        return deleteByKey( rendaFixa.getId() );
    }

    @Override
    public List<Ativo> findAllByGrupo( Grupo grupo ) {
        return null;
    }
}
