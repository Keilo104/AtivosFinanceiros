package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.DAOs.AtivosDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.DAOs.FundoDeInvestimentoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteFundoDeInvestimentoDAO implements FundoDeInvestimentoDAO {
    @Override
    public Integer create(FundoDeInvestimento fundoDeInvestimento) {
        AtivosDAO ativosDAO = new sqliteAtivosDAO();
        int id = ativosDAO.create(fundoDeInvestimento);
        fundoDeInvestimento.setId(id);

        String sql = "INSERT INTO FUNDO_-DE_INVESTIMENTO VALUES(?,?,?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, fundoDeInvestimento.getId());
            stat.setString(2, fundoDeInvestimento.getNome());
            stat.setString(3, fundoDeInvestimento.getRentabilidade());
            stat.setString(4, fundoDeInvestimento.getLiquidez());
            stat.setFloat(5, fundoDeInvestimento.getTaxaAdministrativa());

            stat.execute();

            return fundoDeInvestimento.getId();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void resultSetToEntity(ResultSet rs, FundoDeInvestimento fundoDeInvestimento) throws SQLException {
        fundoDeInvestimento.setNome(rs.getString("nome"));
        fundoDeInvestimento.setRentabilidade(rs.getString("rentabilidade"));
        fundoDeInvestimento.setLiquidez(rs.getString("liquidez"));
        fundoDeInvestimento.setTaxaAdministrativa(rs.getFloat("taxaAdministrativa"));
    }

    @Override
    public Optional<FundoDeInvestimento> findOne(Integer idAtivo) {
        String sql = "SELECT * FROM FUNDO_DE_INVESTIMENTO WHERE idAtivo = ?";
        FundoDeInvestimento fundoDeInvestimento = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, idAtivo);
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt("idAtivo");
                fundoDeInvestimento = (FundoDeInvestimento) ativosDAO.findOne(id).get();

                resultSetToEntity(rs, fundoDeInvestimento);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(fundoDeInvestimento);
    }

    @Override
    public List<FundoDeInvestimento> findAll() {
        String sql = "SELECT * FROM FUNDO_DE_INVESTIMENTO;";
        List<FundoDeInvestimento> fundos = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt("idAtivo");
                FundoDeInvestimento fundoDeInvestimento = (FundoDeInvestimento) ativosDAO.findOne(id).get();

                resultSetToEntity(rs, fundoDeInvestimento);
                fundos.add(fundoDeInvestimento);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fundos;
    }

    @Override
    public boolean update(FundoDeInvestimento fundoDeInvestimento) {
        String sql = "UPDATE ACAO SET nome=?, rentabilidade=?, liquidez=?, taxaAdministrativa=?  WHERE idAtivo=?";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, fundoDeInvestimento.getNome());
            stat.setString(2, fundoDeInvestimento.getRentabilidade());
            stat.setString(3, fundoDeInvestimento.getLiquidez());
            stat.setFloat(4, fundoDeInvestimento.getTaxaAdministrativa());
            stat.setInt(5, fundoDeInvestimento.getId());

            stat.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer idAtivo) {
        String sql = "DELETE FROM FUNDO_DE_INVESTIMENTO WHERE idAtivo = ?;";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, idAtivo);
            stat.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(FundoDeInvestimento fundoDeInvestimento) {
        if(fundoDeInvestimento == null)
            throw new IllegalArgumentException("Fundo de investimento cannot be null");
        return deleteByKey(fundoDeInvestimento.getId());
    }


    @Override
    public List<FundoDeInvestimento> findAllByGrupo(int idGrupo) {
        String sql = "SELECT * FROM FUNDO_DE_INVESTIMENTO f\n" +
                "JOIN ATIVO a\n" +
                "ON ac.idAtivo = f.id\n" +
                "WHERE a.grupoId = ?;";

        List<FundoDeInvestimento> fundos = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, idGrupo);

            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt("idAtivo");
                FundoDeInvestimento fundoDeInvestimento = (FundoDeInvestimento) ativosDAO.findOne(id).get();

                resultSetToEntity(rs, fundoDeInvestimento);
                fundos.add(fundoDeInvestimento);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fundos;
    }
}
