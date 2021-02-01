package br.edu.ifsp.domain.entities.ativo;

public class InvalidPriceToUpdateException extends RuntimeException {
    public InvalidPriceToUpdateException( String message ) {
        super( message );
    }
}
