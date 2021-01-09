package br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class FundoDeInvestimentoInputValidator extends Validator<FundoDeInvestimento> {
    @Override
    public Notification validate(FundoDeInvestimento fundoDeInvestimento) {
        Notification notification = new Notification();

        if (fundoDeInvestimento == null) {
            notification.addError("Ativo is null");
            return notification;
        }

        if (fundoDeInvestimento.getData() == null) {
            notification.addError("Data cannot be null");
        }
        if (fundoDeInvestimento.getValor() <= 0) {
            notification.addError("Valor cannot be 0 or negative");
        }
        if (fundoDeInvestimento.getQuantidade() <= 0) {
            notification.addError("Quantidade cannot be 0 or negative");
        }
        if (nullOrEmpty(fundoDeInvestimento.getLiquidez())) {
            notification.addError("Liquidez cannot be empty");
        }
        if (nullOrEmpty(fundoDeInvestimento.getRentabilidade())) {
            notification.addError("Rentabilidade cannot be empty");
        }

        return notification;
    }
}
