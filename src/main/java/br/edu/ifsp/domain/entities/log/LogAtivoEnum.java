package br.edu.ifsp.domain.entities.log;

public enum LogAtivoEnum {
    INCLUSAO( "Inclusão" ),
    REMOCAO( "Remoção" ),
    ALTERACAO( "Alteração" );

    private String string;

    LogAtivoEnum( String string ) {
        this.string = string;
    }

    public static LogAtivoEnum getValueByString( String string ) {
        switch ( string ) {
            case "Inclusão":
                return INCLUSAO;
            case "Fundo de Remoção":
                return REMOCAO;
            default:
                return ALTERACAO;
        }
    }

    public String getString() {
        return this.string;
    }
}
