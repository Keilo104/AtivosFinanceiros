package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import br.edu.ifsp.domain.DAOs.RelatorioDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteRelatorioDAO implements RelatorioDAO {
    @Override
    public Integer create(Relatorio relatorio) {
        String sql = "INSERT INTO RELATORIO(dataImpressao, categoria) VALUES(?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, relatorio.getDataImpressao().toString());
            stat.setString(2, relatorio.getCategoria().getString());

            stat.execute();
            ResultSet resultSet = stat.getGeneratedKeys();
            int idCriado = resultSet.getInt(1);
            return idCriado;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Relatorio resultSetToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        LocalDateTime dataImpressao = LocalDateTime.parse(rs.getString("dataImpressao"));
        String categoria = rs.getString("categoria");

        return new Relatorio(id, dataImpressao, TipoGrupoEnum.getValueByString(categoria));
    }

    @Override
    public Optional<Relatorio> findOne(Integer id) {
        String sql = "SELECT * FROM RELATORIO WHERE id = ?";
        Relatorio relatorio = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                relatorio = resultSetToEntity(rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(relatorio);
    }

    @Override
    public List<Relatorio> findAll() {
        String sql = "SELECT * FROM RELATORIO;";
        List<Relatorio> relatorios = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                Relatorio relatorio = resultSetToEntity(rs);
                relatorios.add(relatorio);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return relatorios;
    }

    @Override
    public boolean update(Relatorio relatorio) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM RELATORIO WHERE id = ?;";
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
    public boolean delete(Relatorio relatorio) {
        if(relatorio == null )
            throw new IllegalArgumentException("Grupo cannot be null");
        return deleteByKey(relatorio.getId());
    }
}
