package br.edu.ifsp.domain.usecases.ativo.rendafixa;

import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class RendaFixaInputValidator extends Validator<RendaFixa> {
    @Override
    public Notification validate(RendaFixa rendaFixa) {
        Notification notification = new Notification();

        if (rendaFixa == null) {
            notification.addError("Ativo is null");
            return notification;
        }

        if (rendaFixa.getData() == null) {
            notification.addError("Data cannot be null");
        }
        if (rendaFixa.getValor() <= 0) {
            notification.addError("Valor cannot be 0 or negative");
        }
        if (rendaFixa.getQuantidade() <= 0) {
            notification.addError("Quantidade cannot be 0 or negative");
        }
        if (nullOrEmpty(rendaFixa.getGarantia())) {
            notification.addError("Garantia cannot be empty");
        }
        if (nullOrEmpty(rendaFixa.getTributacao())) {
            notification.addError("Tributacao cannot be empty");
        }

        return notification;
    }
}
