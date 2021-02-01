package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.DAOs.AtivosDAO;
import br.edu.ifsp.domain.DAOs.FundoDeInvestimentoDAO;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteFundoDeInvestimentoDAO implements FundoDeInvestimentoDAO {
    @Override
    public Integer create( FundoDeInvestimento fundoDeInvestimento ) {
        AtivosDAO ativosDAO = new sqliteAtivosDAO();
        int id = ativosDAO.create( fundoDeInvestimento );
        fundoDeInvestimento.setId( id );

        String sql = "INSERT INTO FUNDO_DE_INVESTIMENTO VALUES(?,?,?,?,?);";
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setInt( 1, fundoDeInvestimento.getId() );
            stat.setString( 2, fundoDeInvestimento.getNome() );
            stat.setString( 3, fundoDeInvestimento.getRentabilidade() );
            stat.setString( 4, fundoDeInvestimento.getLiquidez() );
            stat.setFloat( 5, fundoDeInvestimento.getTaxaAdministrativa() );

            stat.execute();

            return fundoDeInvestimento.getId();
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void resultSetToEntity( ResultSet rs, FundoDeInvestimento fundoDeInvestimento ) throws SQLException {
        fundoDeInvestimento.setNome( rs.getString( "nome" ) );
        fundoDeInvestimento.setRentabilidade( rs.getString( "rentabilidade" ) );
        fundoDeInvestimento.setLiquidez( rs.getString( "liquidez" ) );
        fundoDeInvestimento.setTaxaAdministrativa( rs.getFloat( "taxaAdministrativa" ) );
    }

    @Override
    public Optional<FundoDeInvestimento> findOne( Integer idAtivo ) {
        String sql = "SELECT * FROM FUNDO_DE_INVESTIMENTO WHERE idAtivo = ?";
        FundoDeInvestimento fundoDeInvestimento = null;
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setInt( 1, idAtivo );
            ResultSet rs = stat.executeQuery();

            if ( rs.next() ) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt( "idAtivo" );
                fundoDeInvestimento = new FundoDeInvestimento( ativosDAO.findOne( id ).get() );

                resultSetToEntity( rs, fundoDeInvestimento );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable( fundoDeInvestimento );
    }

    @Override
    public List<FundoDeInvestimento> findAll() {
        String sql = "SELECT * FROM FUNDO_DE_INVESTIMENTO;";
        List<FundoDeInvestimento> fundos = new ArrayList<>();
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            ResultSet rs = stat.executeQuery();
            while ( rs.next() ) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt( "idAtivo" );
                FundoDeInvestimento fundoDeInvestimento = new FundoDeInvestimento( ativosDAO.findOne( id ).get() );

                resultSetToEntity( rs, fundoDeInvestimento );
                fundos.add( fundoDeInvestimento );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return fundos;
    }

    @Override
    public boolean update( FundoDeInvestimento fundoDeInvestimento ) {
        String sql = "UPDATE FUNDO_DE_INVESTIMENTO SET nome=?, rentabilidade=?, liquidez=?, taxaAdministrativa=?  WHERE idAtivo=?";
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, fundoDeInvestimento.getNome() );
            stat.setString( 2, fundoDeInvestimento.getRentabilidade() );
            stat.setString( 3, fundoDeInvestimento.getLiquidez() );
            stat.setFloat( 4, fundoDeInvestimento.getTaxaAdministrativa() );
            stat.setInt( 5, fundoDeInvestimento.getId() );

            stat.execute();
            AtivosDAO ativosDAO = new sqliteAtivosDAO();

            return ativosDAO.update( fundoDeInvestimento );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey( Integer idAtivo ) {
        String sql = "DELETE FROM FUNDO_DE_INVESTIMENTO WHERE idAtivo = ?;";
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setInt( 1, idAtivo );
            stat.execute();
            return true;
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete( FundoDeInvestimento fundoDeInvestimento ) {
        if ( fundoDeInvestimento == null )
            throw new IllegalArgumentException( "Fundo de investimento cannot be null" );
        return deleteByKey( fundoDeInvestimento.getId() );
    }

    @Override
    public List<Ativo> findAllByGrupo( Grupo grupo ) {
        String sql = "SELECT * FROM FUNDO_DE_INVESTIMENTO f\n" +
                "JOIN ATIVO a\n" +
                "ON f.idAtivo = a.id\n" +
                "WHERE a.grupoId = ? AND a.quantidade != 0;";

        List<Ativo> fundos = new ArrayList<>();
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setInt( 1, grupo.getId() );

            ResultSet rs = stat.executeQuery();
            while ( rs.next() ) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt( "idAtivo" );
                FundoDeInvestimento fundoDeInvestimento = new FundoDeInvestimento( ativosDAO.findOne( id ).get() );
                fundoDeInvestimento.addObserver( grupo );

                resultSetToEntity( rs, fundoDeInvestimento );
                fundos.add( fundoDeInvestimento );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return fundos;
    }

    public List<String> gerarRelatorio() {
        String sql = "select la.idAtivo , la.data, la.tipo, valor, quantidade from LOG_TRANSACAO_ATIVO la join FUNDO_DE_INVESTIMENTO fi on fi.idAtivo = la.idAtivo UNION select l.idAtivo , l.data, l.tipo, null as valor, null as quantidade from LOG_ATIVO l join FUNDO_DE_INVESTIMENTO fi on fi.idAtivo = l.idAtivo order by l.data;";
        List<String> rel = new ArrayList<>();
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            ResultSet rs = stat.executeQuery();
            rel.add( "Relatório de Fundo de Investimento\n" );
            while ( rs.next() ) {
                String linha = "";
                linha += "\nId ddo fundo de investimento:" + rs.getString( "idAtivo" );
                linha += " Data da acorrência: " + rs.getString( "data" );
                linha += " Tipo da ocorrência:" + rs.getString( "tipo" );
                linha += " Valor:" + rs.getFloat( "valor" );
                linha += " Quantidade: " + rs.getInt( "quantidade" );

                rel.add( linha );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return rel;
    }

    public List<String> gerarRelatorioPeriodo( LocalDate dataInicial, LocalDate dataFinal ) {
        String sql = "select la.idAtivo , la.data, la.tipo, valor, quantidade from LOG_TRANSACAO_ATIVO la join FUNDO_DE_INVESTIMENTO fi on fi.idAtivo = la.idAtivo WHERE data BETWEEN ? and ? UNION select l.idAtivo , l.data, l.tipo, null as valor, null as quantidade from LOG_ATIVO l join FUNDO_DE_INVESTIMENTO fi on fi.idAtivo = l.idAtivo WHERE data BETWEEN ? and ?  order by l.data;";
        List<String> rel = new ArrayList<>();
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, dataInicial.toString() );
            stat.setString( 2, dataFinal.toString() );
            stat.setString( 3, dataInicial.toString() );
            stat.setString( 4, dataFinal.toString() );
            ResultSet rs = stat.executeQuery();
            rel.add( "Relatório de Fundo de Investimento do período:\n" );
            rel.add( dataInicial.toString() + " a " );
            rel.add( dataFinal.toString() + "\n" );
            while ( rs.next() ) {
                String linha = "";
                linha += "\nId do fundo de investimento:" + rs.getString( "idAtivo" );
                linha += " Data da acorrência: " + rs.getString( "data" );
                linha += " Tipo da ocorrência:" + rs.getString( "tipo" );
                linha += " Valor:" + rs.getFloat( "valor" );
                linha += " Quantidade: " + rs.getInt( "quantidade" );

                rel.add( linha );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return rel;
    }
}
