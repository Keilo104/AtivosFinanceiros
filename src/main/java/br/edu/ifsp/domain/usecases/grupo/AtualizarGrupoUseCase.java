package br.edu.ifsp.domain.usecases.grupo;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AtualizarGrupoUseCase {
    private GrupoDAO grupoDAO;

    public AtualizarGrupoUseCase(GrupoDAO grupoDAO) {
        this.grupoDAO = grupoDAO;
    }

    public boolean update(Grupo grupo){
        Validator<Grupo> validator = new GrupoInputValidator();
        Notification notif = validator.validate(grupo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        boolean flag = this.grupoDAO.update(grupo);
        return flag;
    }

}