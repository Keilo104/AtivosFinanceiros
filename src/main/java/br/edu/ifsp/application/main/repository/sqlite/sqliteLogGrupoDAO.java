package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.log.LogGrupo;
import br.edu.ifsp.domain.usecases.log.loggrupo.LogGrupoDAO;
import javafx.util.Pair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteLogGrupoDAO implements LogGrupoDAO {
    @Override
    public Pair<LocalDateTime, Ativo> create(LogGrupo logGrupo) {
        String sql = "INSERT INTO LOG_GRUPO(idGrupo, data,valorTotal, mudanca) VALUES(?,?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            LocalDate data =  LocalDate.now();
            stat.setInt(1, logGrupo.getGrupo().getId());
            stat.setString(2, data.toString());
            stat.setFloat(3, logGrupo.getValorTotal());
            stat.setFloat(4, logGrupo.getMudanca());

            stat.execute();
            Grupo grupo = logGrupo.getGrupo();
            return new Pair<>(data, grupo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private LogGrupo resultSetToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("idGrupo");
        LocalDateTime data = LocalDateTime.parse(rs.getString("data"));
        Float valorTotal = rs.getFloat("valorTotal");
        Float mudanca = rs.getFloat("mudanca");

        Grupo grupo = new sqliteGrupoDAO().findOneGrupo(id);

        return new LogGrupo(data, grupo, valorTotal, mudanca);
    }

    @Override
    public Optional<LogGrupo> findOne(Pair<LocalDate, Grupo> key) {
        String sql = "SELECT * FROM LOG_GRUPO WHERE data = ? AND idGrupo = ?";
        LogGrupo logGrupo = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, key.getKey().toString());
            stat.setInt(2, key.getValue().getId());
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                logGrupo = resultSetToEntity(rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(logGrupo);
    }

    @Override
    public List<LogGrupo> findAll() {
        String sql = "SELECT * FROM LOG_ATIVO;";
        List<LogGrupo> logsGrupo = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                LogGrupo logGrupo = resultSetToEntity(rs);
                logsGrupo.add(logGrupo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return logsGrupo;
    }

    public List<LogGrupo> findAllOrderByData() {
        String sql = "SELECT * FROM LOG_GRUPO ORDER BY data;";
        List<LogGrupo> logsGrupo = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                LogGrupo logGrupo = resultSetToEntity(rs);
                logsGrupo.add(logGrupo);
                System.out.println(logGrupo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return logsGrupo;
    }

    @Override
    public boolean update(LogGrupo type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Pair<LocalDate, Grupo> key) {
        return false;
    }

    @Override
    public boolean delete(LogGrupo type) {
        return false;
    }
}
