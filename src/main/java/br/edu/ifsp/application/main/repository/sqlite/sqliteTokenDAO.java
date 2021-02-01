package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.DAOs.TokenDAO;
import br.edu.ifsp.domain.entities.usuario.Token;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteTokenDAO implements TokenDAO {

    public String create( Token token ) {
        String sql = "INSERT INTO TOKEN ( data, cpfUsuario, token ) VALUES (?, ?, ?)";
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            MessageDigest md = MessageDigest.getInstance( "MD5" );
            String t = "AtivosFinanceiros" + LocalDateTime.now().toString() + token.getUsuario().getCpf();
            byte[] m = md.digest( t.getBytes( StandardCharsets.UTF_8 ) );
            BigInteger no = new BigInteger( 1, m );

            String tokenHash = no.toString( 16 );
            while ( tokenHash.length() < 32 ) {
                tokenHash = "0" + tokenHash;
            }

            stat.setString( 1, token.getDateTime().toString() );
            stat.setString( 2, token.getUsuario().getCpf() );
            stat.setString( 3, tokenHash );

            stat.execute();
            return tokenHash;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Token> findOne( String key ) {
        return Optional.empty();
    }

    public boolean findIfExists( String token ) {
        String sql = "SELECT * FROM TOKEN WHERE token = ?";

        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, token );
            ResultSet rs = stat.executeQuery();
            if ( rs.next() ) {
                return true;
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    private Token resultSetToEntity( ResultSet rs ) throws SQLException {
        int id = rs.getInt( "id" );
        LocalDateTime dateTime = LocalDateTime.parse( rs.getString( "data" ) );
        String cpf = rs.getString( "cpfUsuario" );
        String token = rs.getString( "token" );

        return new Token( token, dateTime );
    }

    @Override
    public List<Token> findAll() {
        String sql = "SELECT * FROM token";
        List<Token> tokens = new ArrayList<>();
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            ResultSet rs = stat.executeQuery();
            while ( rs.next() ) {
                Token token = resultSetToEntity( rs );
                tokens.add( token );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return tokens;
    }

    @Override
    public boolean update( Token token ) {
        String sql = "UPDATE token SET usuario = ?, token = ?, dateTime = ? WHERE id = ?";
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, token.getUsuario().getCpf() );
            stat.setString( 2, token.getToken() );
            stat.setString( 3, token.getDateTime().toString() );
            stat.setInt( 4, token.getId() );
            stat.execute();
            return true;
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey( String token ) {
        String sql = "DELETE FROM TOKEN WHERE token = ?;";
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, token );
            stat.execute();
            return true;
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete( Token type ) {
        return false;
    }
}
