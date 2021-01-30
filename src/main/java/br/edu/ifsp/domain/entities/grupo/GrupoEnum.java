package br.edu.ifsp.domain.entities.grupo;

import java.util.Arrays;

public enum GrupoEnum {
    ACAO ("br.edu.ifsp.domain.entities.ativo.Acao", "Ação"),
    FUNDO_DE_INVESTIMENTO("br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento", "Fundo de Investimento"),
    RENDA_FIXA("br.edu.ifsp.domain.entities.ativo.RendaFixa", "Renda Fixa");
    private String nomeClasse;
    private String string;

    GrupoEnum(String nomeClasse, String string) {
        this.nomeClasse = nomeClasse;
        this.string = string;
    }
    public String getNomeClasse() {
        return this.nomeClasse;
    }
    public String getString() {
        return this.string;
    }
    public static GrupoEnum getValueByString(String string) {
        switch(string) {
            case "Ação":
                return ACAO;
            case "Fundo de Investimento":
                return FUNDO_DE_INVESTIMENTO;
            default:
                return RENDA_FIXA;
        }
    }

    public static GrupoEnum toEnum(String value){
        return  getValueByString(value);
    }
}
