package br.edu.ifsp.domain.usecases.log.loggrupo;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.log.LogGrupo;
import br.edu.ifsp.domain.usecases.utils.DAO;
import javafx.util.Pair;

import java.time.LocalDateTime;

public interface LogGrupoDAO extends DAO<LogGrupo, Pair<LocalDateTime, Grupo>> {
}
