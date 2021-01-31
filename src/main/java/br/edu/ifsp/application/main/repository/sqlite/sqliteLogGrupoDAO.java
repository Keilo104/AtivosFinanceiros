package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.log.LogGrupo;
import br.edu.ifsp.domain.usecases.log.loggrupo.LogGrupoDAO;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class sqliteLogGrupoDAO implements LogGrupoDAO {
    @Override
    public Pair<LocalDateTime, Grupo> create(LogGrupo type) {
        return null;
    }

    @Override
    public Optional<LogGrupo> findOne(Pair<LocalDateTime, Grupo> key) {
        return Optional.empty();
    }

    @Override
    public List<LogGrupo> findAll() {
        return null;
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
    public boolean deleteByKey(Pair<LocalDateTime, Grupo> key) {
        return false;
    }

    @Override
    public boolean delete(LogGrupo type) {
        return false;
    }
}
