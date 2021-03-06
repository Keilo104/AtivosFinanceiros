package br.edu.ifsp.application.main.repository.inMemory;

import br.edu.ifsp.domain.DAOs.AcaoDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryAcaoDAO implements AcaoDAO {
    private static Map<Integer, Ativo> db;
    private static int idCounter;

    public InMemoryAcaoDAO( Map<Integer, Ativo> db ) {
        InMemoryAcaoDAO.db = db;
    }

    @Override
    public Integer create( Acao acao ) {
        idCounter++;
        acao.setId( idCounter );
        db.put( idCounter, acao );
        return idCounter;
    }

    @Override
    public Optional<Acao> findOne( Integer key ) {
        if ( db.containsKey( key ) ) {
            return Optional.of( ( Acao ) db.get( key ) );
        }
        return Optional.empty();
    }

    @Override
    public List<Acao> findAll() {
        ArrayList<Acao> acaoList = new ArrayList<>();

        for ( Ativo a : db.values() ) {
            if ( a instanceof Acao )
                acaoList.add( ( Acao ) a );
        }

        return acaoList;
    }

    @Override
    public boolean update( Acao acao ) {
        Integer id = acao.getId();
        if ( db.containsKey( id ) ) {
            db.replace( id, acao );

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
    public boolean delete( Acao acao ) {
        return deleteByKey( acao.getId() );
    }

    @Override
    public List<Ativo> findAllByGrupo( Grupo grupo ) {
        return null;
    }

    @Override
    public List<Acao> findAllWithoutGroup() {
        return null;
    }
}
