package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.usuario.Token;
import br.edu.ifsp.domain.usecases.usuario.TokenDAO;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class sqliteTokenDAO implements TokenDAO {
    @Override
    public Integer create( Token token ) {
        String sql = "INSERT INTO token ( data, cpfUsuario, token ) VALUES (?, ?, ?)";

        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            String tokenHash = String.valueOf( MessageDigest.getInstance( "AtivosFinanceiros" + LocalDateTime.now() + token.getUsuario().getCpf() ) );

            stat.setString( 1, token.getDateTime().toString() );
            stat.setString( 2, token.getUsuario().getCpf() );
            stat.setString( 3, tokenHash );
            return 1;
        } catch ( Exception e ) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Optional<Token> findOne( Integer key ) {
        return Optional.empty();
    }

    public boolean findOne( String token ) {
        String sql = "SELECT * FROM token WHERE token = ?";

        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, token );
            return true;
        } catch ( Exception e ) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Token> findAll() {
        return null;
    }

    @Override
    public boolean update( Token token ) {
        return false;
    }

    @Override
    public boolean deleteByKey( Integer key ) {
        return false;
    }

    @Override
    public boolean delete( Token token ) {
        return false;
    }
}
