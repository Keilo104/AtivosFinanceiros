package br.edu.ifsp.domain.usecases.relatorio;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class RelatorioValidator extends Validator<Relatorio> {

    @Override
    public Notification validate(Relatorio relatorio) {
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

        return notification;
    }
}
