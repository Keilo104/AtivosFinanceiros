package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.DAOs.LogTransacaoDAO;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivoEnum;
import javafx.util.Pair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteLogTransacaoDAO implements LogTransacaoDAO {
    @Override
    public Pair<LocalDateTime, Ativo> create( LogTransacaoAtivo logTransacaoAtivo ) {
        String sql = "INSERT INTO LOG_TRANSACAO_ATIVO(idAtivo,data,tipo,valor,quantidade) VALUES(?,?,?,?,?);";
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            LocalDateTime data = LocalDateTime.now();
            stat.setInt( 1, logTransacaoAtivo.getAtivo().getId() );
            stat.setString( 2, data.toString() );
            stat.setString( 3, logTransacaoAtivo.getTipo().getString() );
            stat.setFloat( 4, logTransacaoAtivo.getValor() );
            stat.setInt( 5, logTransacaoAtivo.getQuantidade() );

            stat.execute();
            Ativo ativo = logTransacaoAtivo.getAtivo();
            return new Pair<>( data, ativo );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return null;
    }

    private LogTransacaoAtivo resultSetToEntity( ResultSet rs ) throws SQLException {
        int id = rs.getInt( "idAtivo" );
        LocalDateTime data = LocalDateTime.parse( rs.getString( "data" ) );
        String tipo = rs.getString( "tipo" );
        float valor = rs.getFloat( "valor" );
        int quantidade = rs.getInt( "quantidade" );

        Ativo ativo = new sqliteAtivosDAO().findOneAtivo( id );

        return new LogTransacaoAtivo( data, LogTransacaoAtivoEnum.getValueByString( tipo ), ativo, valor, quantidade );
    }

    @Override
    public Optional<LogTransacaoAtivo> findOne( Pair<LocalDateTime, Ativo> key ) {
        String sql = "SELECT * FROM LOG_GRUPO WHERE data = ? AND idAtivo = ?";
        LogTransacaoAtivo logTransacaoAtivo = null;
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, key.getKey().toString() );
            stat.setInt( 2, key.getValue().getId() );
            ResultSet rs = stat.executeQuery();

            if ( rs.next() ) {
                logTransacaoAtivo = resultSetToEntity( rs );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable( logTransacaoAtivo );
    }

    @Override
    public List<LogTransacaoAtivo> findAll() {
        String sql = "SELECT * FROM LOG_TRANSACAO_ATIVO;";
        List<LogTransacaoAtivo> logsTransacaoAtivo = new ArrayList<>();
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            ResultSet rs = stat.executeQuery();
            while ( rs.next() ) {
                LogTransacaoAtivo logTransacaoAtivo = resultSetToEntity( rs );
                logsTransacaoAtivo.add( logTransacaoAtivo );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return logsTransacaoAtivo;
    }

    @Override
    public boolean update( LogTransacaoAtivo type ) {
        return false;
    }

    @Override
    public boolean deleteByKey( Pair<LocalDateTime, Ativo> key ) {
        return false;
    }

    @Override
    public boolean delete( LogTransacaoAtivo type ) {
        return false;
    }
}
