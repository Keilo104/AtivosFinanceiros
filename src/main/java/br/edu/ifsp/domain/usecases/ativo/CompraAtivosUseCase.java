package br.edu.ifsp.domain.usecases.ativo;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.grupo.GrupoDAO;
import br.edu.ifsp.domain.usecases.grupo.GrupoInputValidator;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CompraAtivosUseCase {
    private AtivosDAO ativosDAO;
    private GrupoDAO grupoDAO;

    public CompraAtivosUseCase( AtivosDAO ativoDAO, GrupoDAO grupoDAO ) {
        this.ativosDAO = ativoDAO;
        this.grupoDAO = grupoDAO;
    }

    public CompraAtivosUseCase() {
    }

    public boolean compraAtivo(Grupo grupo, Ativo ativo) {
        Validator<Ativo> validatorAtivo = new AtivosValidator();
        Validator<Grupo> validatorGrupo = new GrupoInputValidator();

        Notification notif = validatorAtivo.validate(ativo);
        notif.addErrors(validatorGrupo.validate(grupo));

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        if(ativosDAO.update(ativo)) {
            grupo.addAtivo(ativo);
            return grupoDAO.update(grupo);
        }

        return false;
    }
}
