package br.edu.ifsp.domain.usecases.log.logativo;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.usecases.utils.DAO;
import javafx.util.Pair;

import java.time.LocalDate;

public interface LogAtivoDAO extends DAO<LogAtivo, Pair<LocalDate, Ativo>> {
}
