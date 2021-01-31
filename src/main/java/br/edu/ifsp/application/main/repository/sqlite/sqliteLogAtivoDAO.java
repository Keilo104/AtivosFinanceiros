package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogAtivoEnum;
import br.edu.ifsp.domain.usecases.log.logativo.LogAtivoDAO;
import javafx.util.Pair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteLogAtivoDAO implements LogAtivoDAO {

    @Override
    public Pair<LocalDateTime, Ativo> create(LogAtivo logAtivo) {
        String sql = "INSERT INTO LOG_ATIVO(idAtivo, data,tipo) VALUES(?,?,?);";
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            LocalDateTime data =  LocalDateTime.now();
            stat.setInt(1, logAtivo.getAtivo().getId());
            stat.setString(2, data.toString());
            stat.setString(3, logAtivo.getTipo().getString());

            stat.execute();
            Ativo ativo = logAtivo.getAtivo();
            return new Pair<>(data, ativo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private LogAtivo resultSetToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("idAtivo");
        LocalDateTime data = LocalDateTime.parse(rs.getString("data"));
        String tipo = rs.getString("tipo");

        Ativo ativo = new sqliteAtivosDAO().findOneAtivo(id);

        return new LogAtivo(data, ativo, LogAtivoEnum.getValueByString(tipo));
    }

    @Override
    public Optional<LogAtivo> findOne(Pair<LocalDateTime, Ativo> key) {
        String sql = "SELECT * FROM LOG_ATIVO WHERE data = ? AND idAtivo = ?";
        LogAtivo logAtivo = null;
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            stat.setString(1, key.getKey().toString());
            stat.setInt(2, key.getValue().getId());
            ResultSet rs = stat.executeQuery();

            if(rs.next()) {
                logAtivo = resultSetToEntity(rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable(logAtivo);
    }

    @Override
    public List<LogAtivo> findAll() {
        String sql = "SELECT * FROM LOG_ATIVO;";
        List<LogAtivo> logsAtivo = new ArrayList<>();
        try (PreparedStatement stat = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                LogAtivo logAtivo = resultSetToEntity(rs);
                logsAtivo.add(logAtivo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return logsAtivo;
    }

    @Override
    public boolean update(LogAtivo type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Pair<LocalDateTime, Ativo> key) {
        return false;
    }

    @Override
    public boolean delete(LogAtivo type) {
        return false;
    }
}
