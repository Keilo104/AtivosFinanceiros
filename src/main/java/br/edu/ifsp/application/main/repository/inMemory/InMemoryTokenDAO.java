package br.edu.ifsp.application.main.repository.inMemory;

import br.edu.ifsp.domain.DAOs.TokenDAO;
import br.edu.ifsp.domain.entities.usuario.Token;

import java.util.*;

public class InMemoryTokenDAO implements TokenDAO {
    private static final Map<Integer, Token> db = new LinkedHashMap<>();
    private static int idCounter;

    public String create( Token token ) {
        idCounter++;
        token.setId( idCounter );
        db.put( idCounter, token );
        return String.valueOf( idCounter );
    }

    @Override
    public Optional<Token> findOne( String key ) {
        return Optional.empty();
    }

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
    public boolean deleteByKey( String key ) {
        return false;
    }

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

    @Override
    public boolean findIfExists( String token ) {
        return false;
    }
}
