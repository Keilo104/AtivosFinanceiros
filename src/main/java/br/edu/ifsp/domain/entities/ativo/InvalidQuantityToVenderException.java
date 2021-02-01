package br.edu.ifsp.domain.entities.ativo;

public class InvalidQuantityToVenderException extends RuntimeException {
    public InvalidQuantityToVenderException( String message ) {
        super( message );
    }
}
