package br.edu.ifsp.domain.entities.ativo;

import java.time.LocalDate;

public class FundoDeInvestimento extends Ativo{
    private String nome;
    private String rentabilidade;
    private String liquidez;
    private float taxaAdministrativa;

    public FundoDeInvestimento() {
        super();
    }

    public FundoDeInvestimento(float valor, int quantidade, String nome, String rentabilidade, String liquidez, float taxaAdministrativa) {
        super(valor, quantidade);
        this.nome = nome;
        this.rentabilidade = rentabilidade;
        this.liquidez = liquidez;
        this.taxaAdministrativa = taxaAdministrativa;
    }

    public FundoDeInvestimento(int id, float valorUnitarioAtual, float valorUnitarioComprado, float valorTotalVendido, LocalDate dataComprado, int quantidade, String nome, String rentabilidade, String liquidez, float taxaAdministrativa) {
        super(id, valorUnitarioAtual, valorUnitarioComprado, valorTotalVendido, dataComprado, quantidade);
        this.nome = nome;
        this.rentabilidade = rentabilidade;
        this.liquidez = liquidez;
        this.taxaAdministrativa = taxaAdministrativa;
    }

    public String getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(String rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public String getLiquidez() {
        return liquidez;
    }

    public void setLiquidez(String liquidez) {
        this.liquidez = liquidez;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getTaxaAdministrativa() {
        return taxaAdministrativa;
    }

    public void setTaxaAdministrativa(float taxaAdministrativa) {
        this.taxaAdministrativa = taxaAdministrativa;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "FundoDeInvestimento{" +
                "nome='" + nome + '\'' +
                ", rentabilidade='" + rentabilidade + '\'' +
                ", liquidez='" + liquidez + '\'' +
                ", taxa_administrativa=" + taxaAdministrativa +
                "} " + super.toString();
    }
}
