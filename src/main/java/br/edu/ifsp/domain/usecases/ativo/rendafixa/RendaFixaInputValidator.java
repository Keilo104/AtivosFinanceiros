package br.edu.ifsp.domain.usecases.ativo.rendafixa;

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

        if (rendaFixa.getValorUnitarioAtual() <= 0) {
            notification.addError("Valor Unitario Atual cannot be 0 or negative");
        }
        if (rendaFixa.getValorUnitarioComprado() <= 0) {
            notification.addError("Valor Unitario Comprado cannot be 0 or negative");
        }
        if (rendaFixa.getDataComprado() == null) {
            notification.addError("Data cannot be null");
        }
        if (rendaFixa.getQuantidade() < 0) {
            notification.addError("Quantidade cannot be less than 0");
        }
        if (nullOrEmptyOrBlank(rendaFixa.getRendimento())) {
            notification.addError("Rendimento cannot be empty");
        }

        return notification;
    }
}
