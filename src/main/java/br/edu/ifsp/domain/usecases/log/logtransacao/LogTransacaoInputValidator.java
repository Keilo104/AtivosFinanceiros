package br.edu.ifsp.domain.usecases.log.logtransacao;

import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class LogTransacaoInputValidator extends Validator<LogTransacaoAtivo> {
    @Override
    public Notification validate( LogTransacaoAtivo logTransacaoAtivo ) {
        Notification notification = new Notification();

        if ( logTransacaoAtivo == null ) {
            notification.addError( "Acao is null" );
            return notification;
        }

        if ( logTransacaoAtivo.getData() == null ) {
            notification.addError( "Data cannot be null" );
        }
        if ( logTransacaoAtivo.getAtivo() == null ) {
            notification.addError( "Ativo cannot be null" );
        }
        if ( logTransacaoAtivo.getTipo() == null ) {
            notification.addError( "Tipo cannot be null" );
        }
        if ( logTransacaoAtivo.getValor() <= 0 ) {
            notification.addError( "Valor cannot be 0 or negative" );
        }
        if ( logTransacaoAtivo.getQuantidade() <= 0 ) {
            notification.addError( "Quantidade cannot be 0 or negative" );
        }

        return notification;
    }
}
