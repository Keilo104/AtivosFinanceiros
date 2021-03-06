package br.edu.ifsp.domain.usecases.ativo;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AtivosValidator extends Validator<Ativo> {

    @Override
    public Notification validate( Ativo ativo ) {
        Notification notification = new Notification();

        if ( ativo == null ) {
            notification.addError( "Ativo is null" );
            return notification;
        }

        if ( ativo.getValorUnitarioAtual() <= 0 ) {
            notification.addError( "Valor Unitario Atual cannot be 0 or negative" );
        }
        if ( ativo.getValorUnitarioComprado() <= 0 ) {
            notification.addError( "Valor Unitario Comprado cannot be 0 or negative" );
        }
        if ( ativo.getDataComprado() == null ) {
            notification.addError( "Data cannot be null" );
        }
        if ( ativo.getQuantidade() < 0 ) {
            notification.addError( "Quantidade cannot be less than 0" );
        }

        return notification;
    }
}
