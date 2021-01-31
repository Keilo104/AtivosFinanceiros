package br.edu.ifsp.domain.usecases.grupo;

import br.edu.ifsp.domain.DAOs.GrupoDAO;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.log.LogGrupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.DAOs.LogGrupoDAO;
import br.edu.ifsp.domain.usecases.log.loggrupo.SalvarHistoricoGrupoUseCase;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarGrupoUseCase {
    private GrupoDAO grupoDAO;
    private LogGrupoDAO logGrupoDAO;

    public CriarGrupoUseCase(GrupoDAO grupoDAO, LogGrupoDAO logGrupoDAO) {
        this.grupoDAO = grupoDAO;
        this.logGrupoDAO = logGrupoDAO;
    }

    public int include(Usuario usuario, Grupo grupo){
        Validator<Grupo> validator = new GrupoInputValidator();
        Notification notif = validator.validate(grupo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        int id = this.grupoDAO.createComCPF(grupo, usuario.getCpf());

        if(id != 0) {
            grupo.setId(id);
            usuario.addGrupo(grupo);

            SalvarHistoricoGrupoUseCase salvarHistoricoGrupoUseCase = new SalvarHistoricoGrupoUseCase(logGrupoDAO);
            LogGrupo logGrupo = new LogGrupo(grupo, 0, 0);
            salvarHistoricoGrupoUseCase.salvarHistorico(usuario, logGrupo);

            return id;
        }
        throw new IllegalArgumentException("Grupo wasnt able to be assigned");
    }
}