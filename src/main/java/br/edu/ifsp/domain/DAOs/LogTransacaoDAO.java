package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import javafx.util.Pair;

import java.time.LocalDateTime;

public interface LogTransacaoDAO extends DAO<LogTransacaoAtivo, Pair<LocalDateTime, Ativo>> {
}
