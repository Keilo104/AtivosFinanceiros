package br.edu.ifsp.domain.usecases.relatorio;

import br.edu.ifsp.domain.DAOs.RelatorioDAO;
import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class GerarRelatorioCategoriaUseCase {
    private RelatorioDAO relatorioDAO;

    public GerarRelatorioCategoriaUseCase(RelatorioDAO relatorioDAO) {
        this.relatorioDAO = relatorioDAO;
    }

    public int gerar(Relatorio relatorio){
        Validator<Relatorio> validator = new RelatorioValidator();
        Notification notif = validator.validate(relatorio);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        Integer id = this.relatorioDAO.create(relatorio);
        return id;
    }
}
