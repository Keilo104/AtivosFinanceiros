package br.edu.ifsp.domain.usecases.relatorio;

import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import br.edu.ifsp.domain.entities.relatorio.RelatorioPeriodo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class RelatorioPeriodoValidator extends Validator<RelatorioPeriodo> {

    @Override
    public Notification validate(RelatorioPeriodo relatorio) {
        Notification notification = new Notification();
        if (relatorio == null) {
            notification.addError("Relatorio is null");
            return notification;
        }

        if (relatorio.getDataImpressao() == null) {
            notification.addError("Data cannot be null");
        }
        if (relatorio.getCategoria() == null) {
            notification.addError("Categoria cannot be null");
        }
        if (relatorio.getDataInicial() == null) {
            notification.addError("Data inicial cannot be null");
        }
        if (relatorio.getDataFinal() == null) {
            notification.addError("Data final cannot be null");
        }

        return notification;
    }
}
