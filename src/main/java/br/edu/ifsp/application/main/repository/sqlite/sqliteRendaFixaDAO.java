package br.edu.ifsp.application.main.repository.sqlite;


import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.RendaFixaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteRendaFixaDAO implements RendaFixaDAO {
    @Override
    public Integer create(RendaFixa rendaFixa) {
        return null;
    }

    @Override
    public Integer create(RendaFixa rendaFixa, Integer idAtivo) {
        String sql = "INSERT INTO RENDA_FIXA VALUES(?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, idAtivo);
            stat.setString(2, rendaFixa.getRendimento());
            stat.setString(3, rendaFixa.getDataVencimento().toString());

            stat.execute();

            return idAtivo;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private RendaFixa resultSetToEntity(ResultSet rs) throws SQLException {
        int idAtivo = rs.getInt("idAtivo");
        String rendimento = rs.getString("rendimento");
        String dataVencimento = rs.getString("dataVencimento");

        return new RendaFixa(idAtivo, rendimento, dataVencimento);
    }

    @Override
    public Optional<RendaFixa> findOne(Integer idAtivo) {
        String sql = "SELECT * FROM RENDA_FIXA WHERE idAtivo = ?";
        RendaFixa rendaFixa = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, idAtivo);
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                rendaFixa = resultSetToEntity(rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(rendaFixa);
    }

    @Override
    public List<RendaFixa> findAll() {
        String sql = "SELECT * FROM RENDA_FIXA;";
        List<RendaFixa> rendas = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                RendaFixa renda = resultSetToEntity(rs);
                rendas.add(renda);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rendas;
    }

    @Override
    public boolean update(RendaFixa rendaFixa) {
        String sql = "UPDATE RENDA_FIXA SET rendimento=?, dataVencimento=? WHERE idAtivo=?";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, rendaFixa.getRendimento());
            stat.setString(2, rendaFixa.getDataVencimento().toString());
            stat.setInt(3, rendaFixa.getId());

            stat.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer idAtivo) {
        String sql = "DELETE FROM RENDA_FIXA WHERE id = ?;";
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
    public boolean delete(RendaFixa rendaFixa) {
        if(rendaFixa == null)
            throw new IllegalArgumentException("Acao cannot be null");
        return deleteByKey(rendaFixa.getId());
    }


}
