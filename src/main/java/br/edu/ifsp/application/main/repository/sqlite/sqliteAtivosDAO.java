package br.edu.ifsp.application.main.repository.sqlite;


import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.ativo.AtivosDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteAtivosDAO implements AtivosDAO {
    @Override
    public Integer create(Ativo ativo) {
        String sql = "INSERT INTO ATIVO(valorUnitarioAtual, valorUnitarioComprado, valorTotalVendido, dataComprado, quantidade, grupoId)"+
                "VALUES(?,?,?,?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setFloat(1, 0);
            stat.setFloat(2, 0);
            stat.setFloat(3, 0);
            stat.setString(4, null);
            stat.setInt(5, 0);
            stat.setInt(6, -1);

            stat.execute();
            ResultSet resultSet = stat.getGeneratedKeys();
            int idCriado = resultSet.getInt(1);
            return idCriado;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Ativo resultSetToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        float valorUnitarioAtual = rs.getFloat("valorUnitarioAtual");
        float valorUnitarioComprado = rs.getFloat("valorUnitarioComprado");
        float valorTotalVendido = rs.getFloat("valorTotalVendido");
        LocalDateTime dataComprado = LocalDateTime.parse(rs.getString("dataComprado"));
        int quantidade = rs.getInt("quantidade");
        int grupoId = rs.getInt("grupoId");

        return new Ativo(id, valorUnitarioAtual,valorUnitarioComprado, valorTotalVendido,dataComprado, quantidade, grupoId );
    }

    @Override
    public Optional<Ativo> findOne(Integer id) {
        String sql = "SELECT * FROM ATIVO WHERE id = ?";
        Ativo ativo = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                ativo = resultSetToEntity(rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(ativo);
    }

    @Override
    public List<Ativo> findAll() {
        String sql = "SELECT * FROM ATIVO;";
        List<Ativo> ativos = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                Ativo ativo = resultSetToEntity(rs);
                ativos.add(ativo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ativos;
    }

    @Override
    public boolean update(Ativo ativo) {
        String sql = "UPDATE ATIVO SET valorUnitarioAtual=?, valorUnitarioComprado=?, valorTotalVendido=?, dataComprado=?, quantidade=?, grupoId=? WHERE id=?" +
                "VALUES(?,?,?,?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setFloat(1, ativo.getValorUnitarioAtual());
            stat.setFloat(2, ativo.getValorUnitarioComprado());
            stat.setFloat(3, ativo.getValorTotalVendido());
            stat.setString(4, ativo.getDataComprado().toString());
            stat.setInt(5, ativo.getQuantidade());
            stat.setInt(6, ativo.getIDGrupo());
            stat.setInt(7, ativo.getId());

            stat.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

        @Override
    public boolean deleteByKey(Integer id) {
            String sql = "DELETE FROM ATIVO WHERE id = ?;";
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
    public boolean delete(Ativo ativo) {
        if(ativo == null)
            throw new IllegalArgumentException("Ativo cannot be null");
        return deleteByKey(ativo.getId());
    }
}
