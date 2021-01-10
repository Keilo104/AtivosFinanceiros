package br.edu.ifsp.domain.usecases.grupo;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarGrupoUseCase {
    private GrupoDAO grupoDAO;

    public CriarGrupoUseCase(GrupoDAO grupoDAO) {
        this.grupoDAO = grupoDAO;
    }

    public int include(Usuario usuario, Grupo grupo){
        Validator<Grupo> validator = new GrupoInputValidator();
        Notification notif = validator.validate(grupo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        int id = this.grupoDAO.create(grupo);

        if(id != 0) {
            usuario.addGrupo(grupo);

            return id;
        }
        throw new IllegalArgumentException("Grupo wasnt able to be assigned");
    }
}