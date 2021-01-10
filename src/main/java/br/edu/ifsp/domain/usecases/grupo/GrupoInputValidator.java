package br.edu.ifsp.domain.usecases.grupo;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class GrupoInputValidator extends Validator<Grupo> {
    @Override
    public Notification validate(Grupo grupo) {
        Notification notification = new Notification();

        if(grupo == null){
            notification.addError("Grupo is null");
            return notification;
        }

        if(nullOrEmpty(grupo.getNome())) {
            notification.addError("Nome cannot be null");
        }

        if(grupo.getId()<=0) {
            notification.addError("Id cannot be <=0>");
        }

        return notification;
    }

    public Notification validateId(Integer id) {
        Notification notification = new Notification();
        if(id<=0) {
            notification.addError("Id cannot be <=0>");
        }
        return notification;
    }

}
