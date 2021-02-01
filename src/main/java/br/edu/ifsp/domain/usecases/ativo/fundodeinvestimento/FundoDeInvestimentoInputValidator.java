package br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento;

import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class FundoDeInvestimentoInputValidator extends Validator<FundoDeInvestimento> {
    @Override
    public Notification validate( FundoDeInvestimento fundoDeInvestimento ) {
        Notification notification = new Notification();

        if ( fundoDeInvestimento == null ) {
            notification.addError( "Ativo is null" );
            return notification;
        }

        if ( fundoDeInvestimento.getValorUnitarioAtual() <= 0 ) {
            notification.addError( "Valor Unitario Atual cannot be 0 or negative" );
        }
        if ( fundoDeInvestimento.getValorUnitarioComprado() <= 0 ) {
            notification.addError( "Valor Unitario Comprado cannot be 0 or negative" );
        }
        if ( fundoDeInvestimento.getDataComprado() == null ) {
            notification.addError( "Data cannot be null" );
        }
        if ( fundoDeInvestimento.getQuantidade() < 0 ) {
            notification.addError( "Quantidade cannot be less than 0" );
        }
        if ( nullOrEmptyOrBlank( fundoDeInvestimento.getNome() ) ) {
            notification.addError( "Nome cannot be empty" );
        }
        if ( nullOrEmptyOrBlank( fundoDeInvestimento.getLiquidez() ) ) {
            notification.addError( "Liquidez cannot be empty" );
        }
        if ( nullOrEmptyOrBlank( fundoDeInvestimento.getRentabilidade() ) ) {
            notification.addError( "Rentabilidade cannot be empty" );
        }
        if ( fundoDeInvestimento.getTaxaAdministrativa() <= 0 ) {
            notification.addError( "Taxa Administrativa cannot be 0 or negative" );
        }

        return notification;
    }
}
