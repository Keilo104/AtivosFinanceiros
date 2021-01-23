package br.edu.ifsp.domain.entities.grupo;

public enum GrupoEnum {

    ACAO ("br.edu.ifsp.domain.entities.ativo.Acao"),
    FUNDO_DE_INVESTIMENTO("br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento"),
    RENDA_FIXA("br.edu.ifsp.domain.entities.ativo.RendaFixa");

    private String nomeClasse;

    GrupoEnum(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public String getNomeClasse() {
        return this.nomeClasse;
    }
}
