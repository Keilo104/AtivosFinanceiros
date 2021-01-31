package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.DAOs.AtivosDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.DAOs.AcaoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteAcaoDAO implements AcaoDAO {
    @Override
    public Integer create(Acao acao) {
        AtivosDAO ativosDAO = new sqliteAtivosDAO();
        int id = ativosDAO.create(acao);
        acao.setId(id);

        String sql = "INSERT INTO ACAO VALUES(?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, acao.getId());
            stat.setString(2, acao.getCodigo());
            stat.setString(3, acao.getPais());

            stat.execute();

            return acao.getId();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Acao resultSetToEntity(ResultSet rs, Acao acao) throws SQLException {
        acao.setCodigo(rs.getString("codigo"));
        acao.setPais(rs.getString("pais"));
    }

    @Override
    public Optional<Acao> findOne(Integer idAtivo) {
        String sql = "SELECT * FROM ACAO WHERE idAtivo = ?";
        Acao acao = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, idAtivo);
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                acao = resultSetToEntity(rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(acao);
    }

    @Override
    public List<Acao> findAll() {
        String sql = "SELECT * FROM ACAO;";
        List<Acao> acoes = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                Acao acao = ativosDAO.findOne(rs.getInt("idAtivo"));

                resultSetToEntity(rs, acao);

                acoes.add(acao);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return acoes;
    }

    @Override
    public List<Acao> findAllByGrupo() {

    }

    @Override
    public boolean update(Acao acao) {
        String sql = "UPDATE ACAO SET codigo=?, pais=? WHERE idAtivo=?";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, acao.getCodigo());
            stat.setString(2, acao.getPais());
            stat.setInt(3, acao.getId());

            stat.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer idAtivo) {
        String sql = "DELETE FROM ACAO WHERE id = ?;";
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
    public boolean delete(Acao acao) {
        if(acao == null)
            throw new IllegalArgumentException("Acao cannot be null");
        return deleteByKey(acao.getId());
    }


}
