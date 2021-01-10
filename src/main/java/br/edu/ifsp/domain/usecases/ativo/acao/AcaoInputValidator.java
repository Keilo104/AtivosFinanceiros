package br.edu.ifsp.domain.usecases.ativo.acao;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AcaoInputValidator extends Validator<Acao> {
    @Override
    public Notification validate(Acao acao) {
        Notification notification = new Notification();

        if (acao == null) {
            notification.addError("Acao is null");
            return notification;
        }

        if (acao.getData() == null) {
            notification.addError("Data cannot be null");
        }
        if (acao.getValorComprado() <= 0) {
            notification.addError("Valor Comprado cannot be 0 or negative");
        }
        if (acao.getValorAtual() <= 0) {
            notification.addError("Valor Atual cannot be 0 or negative");
        }
        if (acao.getQuantidade() <= 0) {
            notification.addError("Quantidade cannot be 0 or negative");
        }
        if (nullOrEmpty(acao.getCategoria())) {
            notification.addError("Categoria cannot be empty");
        }
        if (nullOrEmpty(acao.getPais())) {
            notification.addError("Pais cannot be empty");
        }

        return notification;
    }
}
