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

        return notification;
    }

    public Notification validateNome(String nome) {
        Notification notification = new Notification();
        if(nullOrEmpty(nome)) {
            notification.addError("Nome cannot be null");
        }
        return notification;
    }

}
