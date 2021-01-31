package br.edu.ifsp.domain.entities.log;

import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;

public enum LogAtivoEnum {
    INCLUSAO("Inclusão"),
    REMOCAO("Remoção"),
    ALTERACAO("Alteração");

    private String string;

    LogAtivoEnum(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }

    public static LogAtivoEnum getValueByString(String string) {
        switch(string) {
            case "Inclusão":
                return INCLUSAO;
            case "Fundo de Remoção":
                return REMOCAO;
            default:
                return ALTERACAO;
        }
    }
}
