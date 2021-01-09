package br.edu.ifsp.domain.usecases.log.logtransacao;

import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.usecases.utils.DAO;

public interface LogTransacaoDAO extends DAO<LogTransacaoAtivo, Integer> {
}
