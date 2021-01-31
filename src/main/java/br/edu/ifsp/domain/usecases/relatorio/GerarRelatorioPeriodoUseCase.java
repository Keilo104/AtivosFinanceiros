package br.edu.ifsp.domain.usecases.relatorio;

import br.edu.ifsp.domain.DAOs.RelatorioDAO;
import br.edu.ifsp.domain.entities.relatorio.RelatorioPeriodo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class GerarRelatorioPeriodoUseCase {
    private RelatorioDAO relatorioDAO;

    public GerarRelatorioPeriodoUseCase(RelatorioDAO relatorioDAO) {
        this.relatorioDAO = relatorioDAO;
    }

    public int gerarRelatorioPeriodo (RelatorioPeriodo relatorioPeriodo){
        Validator<RelatorioPeriodo> validator = new RelatorioPeriodoValidator();
        Notification notif = validator.validate(relatorioPeriodo);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        Integer id = this.relatorioDAO.create(relatorioPeriodo);
        return id;
    }


}
