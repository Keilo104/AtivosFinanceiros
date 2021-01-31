package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.usuario.Token;
import br.edu.ifsp.domain.usecases.usuario.TokenDAO;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalDateTimeTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteTokenDAO implements TokenDAO {
    @Override
    public Integer create( Token token ) {
        String sql = "INSERT INTO token ( data, cpfUsuario, token ) VALUES (?, ?, ?)";

        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            String tokenHash = String.valueOf( MessageDigest.getInstance( "AtivosFinanceiros" + LocalDateTimeTime.now() + token.getUsuario().getCpf() ) );

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
    public boolean deleteByKey( Integer key ) {
        return false;
    }

    @Override
    public boolean delete( Token token ) {
        String sql = "DELETE FROM usuario WHERE token = ?;";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, token.getToken());
            stat.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
