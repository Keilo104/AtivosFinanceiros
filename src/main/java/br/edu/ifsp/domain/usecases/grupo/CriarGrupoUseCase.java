package br.edu.ifsp.domain.usecases.grupo;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarGrupoUseCase {
    private GrupoDAO grupoDAO;

    public CriarGrupoUseCase(GrupoDAO grupoDAO) {
        this.grupoDAO = grupoDAO;
    }

    public String include(Grupo grupo){
        Validator<Grupo> validator = new GrupoInputValidator();
        Notification notif = validator.validate(grupo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        String nome = this.grupoDAO.create(grupo);
        return nome;
    }
}