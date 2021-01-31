package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.DAOs.AcaoDAO;
import br.edu.ifsp.domain.DAOs.FundoDeInvestimentoDAO;
import br.edu.ifsp.domain.DAOs.RendaFixaDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.DAOs.GrupoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteGrupoDAO implements GrupoDAO {
    @Override
    public Integer createComCPF(Grupo grupo, String cpf) {
        String sql = "INSERT INTO GRUPO(nome, totalLucrado, totalInvestido, lucroPotencial, valorAtual, investimentoAtual, tipoGrupo, cpfUsuario)"+
                "VALUES(?,?,?,?,?,?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, grupo.getNome());
            stat.setFloat(2, 0);
            stat.setFloat(3, 0);
            stat.setFloat(4, 0);
            stat.setFloat(5, 0);
            stat.setFloat(6, 0);
            stat.setString(7, grupo.getTipoString());
            stat.setString(8, cpf);

            stat.execute();
            ResultSet resultSet = stat.getGeneratedKeys();
            int idCriado = resultSet.getInt(1);
            return idCriado;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer create(Grupo type) {
        return null;
    }

    private Grupo resultSetToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        Float totalLucrado= rs.getFloat("totalLucrado");
        Float totalInvestido = rs.getFloat("totalInvestido");
        Float lucroPotencial= rs.getFloat("lucroPotencial");
        Float valorAtual= rs.getFloat("valorAtual");
        Float investimentoAtual = rs.getFloat("investimentoAtual");
        String tipoGrupo = rs.getString("tipoGrupo");
        String cpfUsuario = rs.getString("cpfUsuario");
        return new Grupo(id, nome, totalLucrado, totalInvestido, lucroPotencial, valorAtual, investimentoAtual, tipoGrupo, cpfUsuario );
    }

    @Override
    public Optional<Grupo> findOne(Integer id) {
        String sql = "SELECT * FROM GRUPO WHERE id = ?";
        Grupo grupo = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                grupo = resultSetToEntity(rs);
                populateGrupo(grupo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(grupo);
    }

    public Grupo findOneGrupo(Integer id) {
        String sql = "SELECT * FROM GRUPO WHERE id = ?";
        Grupo grupo = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                grupo = resultSetToEntity(rs);
                populateGrupo(grupo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return grupo;
    }

    @Override
    public Optional<Grupo> findOneByNome(String nome) {
        String sql = "SELECT * FROM GRUPO WHERE nome = ?";
        Grupo grupo = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, nome);
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                grupo = resultSetToEntity(rs);

                populateGrupo(grupo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(grupo);
    }

    private void populateGrupo(Grupo grupo) {
        switch(grupo.getTipoGrupo()) {
            case ACAO:
                AcaoDAO acaoDAO = new sqliteAcaoDAO();
                List<Ativo> acoes = acaoDAO.findAllByGrupo(grupo.getId());

                grupo.setAtivos(acoes);
                break;
            case FUNDO_DE_INVESTIMENTO:
                FundoDeInvestimentoDAO fundoDeInvestimentoDAO = new sqliteFundoDeInvestimentoDAO();
                List<Ativo> fundosDeInvestimento = fundoDeInvestimentoDAO.findAllByGrupo(grupo.getId());

                grupo.setAtivos(fundosDeInvestimento);

                break;
            default:
                RendaFixaDAO rendaFixaDAO = new sqliteRendaFixaDAO();
                List<Ativo> rendasFixas = rendaFixaDAO.findAllByGrupo(grupo.getId());

                grupo.setAtivos(rendasFixas);
                break;
        }
    }

    @Override
    public List<Grupo> findAllByCpf(String cpf) {
        String sql = "SELECT * FROM GRUPO WHERE cpfUsuario = ?;";
        List<Grupo> grupos = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, cpf);
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                Grupo grupo = resultSetToEntity(rs);
                populateGrupo(grupo);
                grupos.add(grupo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return grupos;
    }

    @Override //TODO
    public Optional<Grupo> findOneByAtivo(Ativo ativo) {
        return Optional.empty();
    }

    @Override
    public List<Grupo> findAll() {
        String sql = "SELECT * FROM GRUPO;";
        List<Grupo> grupos = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                Grupo grupo = resultSetToEntity(rs);
                populateGrupo(grupo);
                grupos.add(grupo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return grupos;
    }

    @Override
    public boolean update(Grupo grupo) {
        String sql = "UPDATE GRUPO SET nome = ?, totalLucrado =?, totalInvestido=?, lucroPotencial=?, valorAtual=?, investimentoAtual=?, tipoGrupo=?, cpfUsuario=? WHERE id=?";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, grupo.getNome());
            stat.setFloat(2, grupo.getTotalLucrado());
            stat.setFloat(3, grupo.getTotalInvestido());
            stat.setFloat(4, grupo.getLucroPotencial());
            stat.setFloat(5, grupo.getValorAtual());
            stat.setFloat(6, grupo.getInvestimentoAtual());
            stat.setString(7, grupo.getTipoString());
            stat.setString(8, grupo.getCpfUsuario());
            stat.setInt(9, grupo.getId());

            stat.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM GRUPO WHERE id = ?;";
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
    public boolean delete(Grupo grupo) {
        if(grupo == null )
            throw new IllegalArgumentException("Grupo cannot be null");
        return deleteByKey(grupo.getId());
    }
}
