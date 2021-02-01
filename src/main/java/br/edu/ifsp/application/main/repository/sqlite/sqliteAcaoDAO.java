package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.DAOs.AtivosDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.DAOs.AcaoDAO;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteAcaoDAO implements AcaoDAO {
    @Override
    public Integer create(Acao acao) {
        AtivosDAO ativosDAO = new sqliteAtivosDAO();
        int id = ativosDAO.create(acao);
        acao.setId(id);

        String sql = "INSERT INTO ACAO VALUES(?,?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, acao.getId());
            stat.setString(2, acao.getCodigo());
            stat.setString(3, acao.getNome());
            stat.setString(4, acao.getPais());

            stat.execute();

            return acao.getId();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void resultSetToEntity(ResultSet rs, Acao acao) throws SQLException {
        acao.setCodigo(rs.getString("codigo"));
        acao.setPais(rs.getString("pais"));
        acao.setNome( rs.getString( "nome" ) );
    }

    @Override
    public Optional<Acao> findOne(Integer idAtivo) {
        String sql = "SELECT * FROM ACAO WHERE idAtivo = ?";
        Acao acao = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, idAtivo);
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt("idAtivo");
                acao = (Acao) ativosDAO.findOne(id).get();

                resultSetToEntity(rs, acao);
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
                int id = rs.getInt("idAtivo");
                Acao acao = new Acao(ativosDAO.findOne(id).get());

                resultSetToEntity(rs, acao);

                acoes.add(acao);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return acoes;
    }

    @Override
    public List<Acao> findAllWithoutGroup() {
        String sql = "SELECT * FROM ACAO ac\n" +
                "JOIN ATIVO at\n" +
                "ON ac.idAtivo = at.id\n" +
                "WHERE at.grupoId = -1;";
        List<Acao> acoes = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt("idAtivo");
                Acao acao = new Acao(ativosDAO.findOne(id).get());

                resultSetToEntity(rs, acao);

                acoes.add(acao);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return acoes;
    }

    @Override
    public List<Ativo> findAllByGrupo(Grupo grupo) {
        String sql = "SELECT * FROM ACAO ac\n" +
                "JOIN ATIVO at\n" +
                "ON ac.idAtivo = at.id\n" +
                "WHERE at.grupoId = ? AND at.quantidade != 0;";
        List<Ativo> acoes = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, grupo.getId());

            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                AtivosDAO ativosDAO = new sqliteAtivosDAO();
                int id = rs.getInt("idAtivo");
                Acao acao = new Acao(ativosDAO.findOne(id).get());
                acao.addObserver(grupo);

                resultSetToEntity(rs, acao);

                acoes.add(acao);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return acoes;
    }

    @Override
    public boolean update(Acao acao) {
        String sql = "UPDATE ACAO SET codigo=?, pais=?, nome=? WHERE idAtivo=?";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, acao.getCodigo());
            stat.setString(2, acao.getPais());
            stat.setString(3, acao.getNome());
            stat.setInt(4, acao.getId());

            stat.execute();

            AtivosDAO ativosDAO = new sqliteAtivosDAO();

            return ativosDAO.update(acao);

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

    public List<String> gerarRelatorio(){
        String sql = "select la.idAtivo , la.data, la.tipo, valor, quantidade from LOG_TRANSACAO_ATIVO la join acao ac on ac.idAtivo = la.idAtivo UNION select l.idAtivo , l.data, l.tipo, null as valor, null as quantidade from LOG_ATIVO l join acao ac on ac.idAtivo = l.idAtivo order by l.data;";
        List<String> rel = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                String linha = "";
                linha+="\nId da ação:" +rs.getString("idAtivo");
                linha+=" Data da acorrência: "+rs.getString("data");
                linha+=" Tipo da ocorrência:"+rs.getString("tipo");
                linha+=" Valor:"+rs.getFloat("valor");
                linha+=" Quantidade"+rs.getInt("quantidade");

                rel.add(linha);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rel;
    }

    public List<String> gerarRelatorioPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        String sql = "select la.idAtivo , la.data, la.tipo, valor, quantidade from LOG_TRANSACAO_ATIVO la join acao ac on ac.idAtivo = la.idAtivo WHERE data BETWEEN ? and ? UNION select l.idAtivo , l.data, l.tipo, null as valor, null as quantidade from LOG_ATIVO l join acao ac on ac.idAtivo = l.idAtivo WHERE data BETWEEN ? and ?  order by l.data;";
        List<String> rel = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, dataInicial.toString());
            stat.setString(2, dataFinal.toString());
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                String linha = "";
                linha+="\nId da ação:" +rs.getString("idAtivo");
                linha+=" Data da acorrência: "+rs.getString("data");
                linha+=" Tipo da ocorrência:"+rs.getString("tipo");
                linha+=" Valor:"+rs.getFloat("valor");
                linha+=" Quantidade"+rs.getInt("quantidade");

                rel.add(linha);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rel;
    }


}
