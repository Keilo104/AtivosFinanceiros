package br.edu.ifsp.domain.usecases.log.loggrupo;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.log.LogGrupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;
import javafx.util.Pair;

import java.time.LocalDateTime;

public class SalvarHistoricoGrupoUseCase {
    private LogGrupoDAO logGrupoDAO;

    public SalvarHistoricoGrupoUseCase(LogGrupoDAO logGrupoDAO) {
        this.logGrupoDAO = logGrupoDAO;
    }

    public Pair<LocalDateTime, Grupo> salvarHistorico(Usuario usuario, LogGrupo logGrupo) {
        Validator<LogGrupo> validator = new LogGrupoInputValidator();
        Notification notif = validator.validate(logGrupo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        Pair<LocalDateTime, Grupo> pair = this.logGrupoDAO.create(logGrupo);

        usuario.addLog(logGrupo);

        return pair;
    }
}
