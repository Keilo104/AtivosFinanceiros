package br.edu.ifsp.domain.usecases.log.logtransacao;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.usecases.utils.DAO;
import javafx.util.Pair;

import java.time.LocalDate;

public interface LogTransacaoDAO extends DAO<LogTransacaoAtivo, Pair<LocalDate, Ativo>> {
}
