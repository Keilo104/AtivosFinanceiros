package br.edu.ifsp.domain.usecases.ativo.acao;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AcaoInputValidator extends Validator<Acao> {
    @Override
    public Notification validate( Acao acao ) {
        Notification notification = new Notification();

        if ( acao == null ) {
            notification.addError( "Acao is null" );
            return notification;
        }

        if ( acao.getDataComprado() == null ) {
            notification.addError( "Data cannot be null" );
        }
        if ( acao.getQuantidade() < 0 ) {
            notification.addError( "Quantidade cannot be less than 0" );
        }
        if ( nullOrEmptyOrBlank( acao.getCodigo() ) ) {
            notification.addError( "Codigo cannot be empty" );
        }
        if ( nullOrEmptyOrBlank( acao.getPais() ) ) {
            notification.addError( "Pais cannot be empty" );
        }

        return notification;
    }
}
