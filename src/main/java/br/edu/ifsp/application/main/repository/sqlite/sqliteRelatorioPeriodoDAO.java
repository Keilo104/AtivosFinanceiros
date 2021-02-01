package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.relatorio.RelatorioPeriodo;
import br.edu.ifsp.domain.DAOs.RelatorioPeriodoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteRelatorioPeriodoDAO implements RelatorioPeriodoDAO {

    @Override
    public Integer create(RelatorioPeriodo relatorioPeriodo) {
        String sql = "INSERT INTO RELATORIO_PERIODO(dataImpressao, categoria, dataInicial, dataFinal) VALUES(?,?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, relatorioPeriodo.getDataImpressao().toString());
            stat.setString(2, relatorioPeriodo.getCategoria().getString());
            stat.setString(3, relatorioPeriodo.getDataInicial().toString());
            stat.setString(4, relatorioPeriodo.getDataFinal().toString());

            stat.execute();
            ResultSet resultSet = stat.getGeneratedKeys();
            int idCriado = resultSet.getInt(1);
            return idCriado;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private RelatorioPeriodo resultSetToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        LocalDate dataImpressao = LocalDate.parse(rs.getString("dataImpressao"));
        String categoria = rs.getString("categoria");
        LocalDate dataInicial = LocalDate.parse(rs.getString("dataInicial"));
        LocalDate dataFinal = LocalDate.parse(rs.getString("dataFinal"));

        return new RelatorioPeriodo(id, dataImpressao, TipoGrupoEnum.getValueByString(categoria), dataInicial, dataFinal);
    }

    @Override
    public Optional<RelatorioPeriodo> findOne(Integer id) {
        String sql = "SELECT * FROM RELATORIO_PERIODO WHERE id = ?";
        RelatorioPeriodo relatorioPeriodo = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                relatorioPeriodo = resultSetToEntity(rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(relatorioPeriodo);
    }

    @Override
    public List<RelatorioPeriodo> findAll() {
        String sql = "SELECT * FROM RELATORIO_PERIODO;";
        List<RelatorioPeriodo> relatorios = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                RelatorioPeriodo relatorioPeriodo = resultSetToEntity(rs);
                relatorios.add(relatorioPeriodo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return relatorios;
    }

    @Override
    public boolean update(RelatorioPeriodo type) {
        return false;
    }


    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM RELATORIO_PERIODO WHERE id = ?;";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, id);
            stat.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(RelatorioPeriodo relatorioPeriodo) {
        if (relatorioPeriodo == null)
            throw new IllegalArgumentException("Grupo cannot be null");
        return deleteByKey(relatorioPeriodo.getId());
    }
}

