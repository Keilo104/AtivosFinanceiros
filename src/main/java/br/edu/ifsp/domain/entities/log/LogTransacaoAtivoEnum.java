package br.edu.ifsp.domain.entities.log;

public enum LogTransacaoAtivoEnum {
    COMPRA( "Compra" ),
    VENDA( "Venda" );

    private String string;

    LogTransacaoAtivoEnum( String string ) {
        this.string = string;
    }

    public static LogTransacaoAtivoEnum getValueByString( String string ) {
        switch ( string ) {
            case "Compra":
                return COMPRA;
            default:
                return VENDA;
        }
    }

    public String getString() {
        return this.string;
    }
}
