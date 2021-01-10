package br.edu.ifsp.domain.usecases.ativo;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.grupo.GrupoDAO;
import br.edu.ifsp.domain.usecases.grupo.GrupoInputValidator;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class VendaAtivosUseCase {
    private AtivosDAO ativosDAO;
    private GrupoDAO grupoDAO;

    public VendaAtivosUseCase( AtivosDAO ativoDAO, GrupoDAO grupoDAO ) {
        this.ativosDAO = ativoDAO;
        this.grupoDAO = grupoDAO;
    }

    public VendaAtivosUseCase() {
    }

    public boolean vendaAtivo(Grupo grupo, Ativo ativo) {
        Validator<Ativo> validatorAtivo = new AtivosValidator();
        Validator<Grupo> validatorGrupo = new GrupoInputValidator();

        Notification notif = validatorAtivo.validate(ativo);
        notif.addErrors(validatorGrupo.validate(grupo));

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        if(ativosDAO.update(ativo)) {
            grupo.removeAtivo(ativo);
            return grupoDAO.update(grupo);
        }

        return false;
    }
}
