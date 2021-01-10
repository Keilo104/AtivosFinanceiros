package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.usuario.Token;
import br.edu.ifsp.domain.usecases.usuario.TokenDAO;

import java.util.*;

public class InMemoryTokenDAO implements TokenDAO {
    private static final Map<Integer, Token> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create( Token token ) {
        idCounter++;
        token.setId( idCounter );
        db.put( idCounter, token );
        return idCounter;
    }

    @Override
    public Optional<Token> findOne( Integer key ) {
        if ( db.containsKey( key ) )
            return Optional.of( db.get( key ) );
        return Optional.empty();
    }

    @Override
    public List<Token> findAll() {
        return new ArrayList<>( db.values() );
    }

    @Override
    public boolean update( Token token ) {
        Integer id = token.getId();
        if ( db.containsKey( id ) ) {
            db.replace( id, token );
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
    public boolean delete( Token token ) {
        return deleteByKey( token.getId() );
    }
}
