package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.DAOs.AtivosDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.DAOs.RendaFixaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteRendaFixaDAO implements RendaFixaDAO {
    @Override
    public Integer create(RendaFixa rendaFixa) {
        AtivosDAO ativosDAO = new sqliteAtivosDAO();
        int id = ativosDAO.create(rendaFixa);
        rendaFixa.setId(id);

        String sql = "INSERT INTO RENDA_FIXA VALUES(?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, rendaFixa.getId());
            stat.setString(2, rendaFixa.getRendimento());
            stat.setString(3, rendaFixa.getDataVencimento().toString());

            stat.execute();

            return rendaFixa.getId();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void resultSetToEntity(ResultSet rs, RendaFixa rendaFixa) throws SQLException {
        String rendimento = rs.getString("rendimento");
        String dataVencimento = rs.getString("dataVencimento");

        rendaFixa.setRendimento(rendimento);
        rendaFixa.setDataVencimento(LocalDateTime.parse(dataVencimento));
    }

    @Override
    public Optional<RendaFixa> findOne(Integer idAtivo) {
        String sql = "SELECT * FROM RENDA_FIXA WHERE idAtivo = ?";
        RendaFixa rendaFixa = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, idAtivo);
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt("idAtivo");
                RendaFixa renda = new RendaFixa(ativosDAO.findOne(id).get());

                resultSetToEntity(rs, renda);
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
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt("idAtivo");
                RendaFixa renda = new RendaFixa(ativosDAO.findOne(id).get());

                resultSetToEntity(rs, renda);
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
            AtivosDAO ativosDAO = new sqliteAtivosDAO();

            return ativosDAO.update(rendaFixa);

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
            throw new IllegalArgumentException("Renda fixa cannot be null");
        return deleteByKey(rendaFixa.getId());
    }

    @Override
    public List<Ativo> findAllByGrupo(int idGrupo) {
        String sql = "SELECT * FROM RENDA_FIXA r\n" +
                "JOIN ATIVO a\n" +
                "ON r.idAtivo = a.id\n" +
                "WHERE a.grupoId = ?;";
        List<Ativo> rendas = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();

            stat.setInt(1, idGrupo);
            while(rs.next()) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt("idAtivo");
                RendaFixa renda = new RendaFixa(ativosDAO.findOne(id).get());

                resultSetToEntity(rs, renda);
                rendas.add(renda);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rendas;
    }
}
