package br.edu.ifsp.domain.usecases.grupo;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class ExcluirGrupoUseCase {
    private GrupoDAO grupoDAO;

    public ExcluirGrupoUseCase(GrupoDAO grupoDAO) {
        this.grupoDAO = grupoDAO;
    }

    public boolean delete(Grupo grupo){
        Validator<Grupo> validator = new GrupoInputValidator();
        Notification notif = validator.validate(grupo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        boolean flag = this.grupoDAO.delete(grupo);
        return flag;
    }

    public boolean deleteByKey(Integer id){
        Validator<Grupo> validator = new GrupoInputValidator();
        Notification notif = ((GrupoInputValidator) validator).validateId(id);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }
        boolean flag = this.grupoDAO.deleteByKey(id);
        return flag;
    }

}