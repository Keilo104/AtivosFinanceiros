package br.edu.ifsp.domain.entities.ativo;

import java.time.LocalDate;

public class FundoDeInvestimento extends Ativo{
    private String nome;
    private String rentabilidade;
    private String liquidez;
    private float taxa_administrativa;

    public FundoDeInvestimento() {
        super();
    }

    public FundoDeInvestimento(float valor, int quantidade, String nome, String rentabilidade, String liquidez, float taxa_administrativa) {
        super(valor, quantidade);
        this.nome = nome;
        this.rentabilidade = rentabilidade;
        this.liquidez = liquidez;
        this.taxa_administrativa = taxa_administrativa;
    }

    public FundoDeInvestimento(int id, float valorUnitarioAtual, float valorUnitarioComprado, float valorTotalVendido, LocalDate dataComprado, int quantidade, String nome, String rentabilidade, String liquidez, float taxa_administrativa) {
        super(id, valorUnitarioAtual, valorUnitarioComprado, valorTotalVendido, dataComprado, quantidade);
        this.nome = nome;
        this.rentabilidade = rentabilidade;
        this.liquidez = liquidez;
        this.taxa_administrativa = taxa_administrativa;
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

    public float getTaxa_administrativa() {
        return taxa_administrativa;
    }

    public void setTaxa_administrativa(float taxa_administrativa) {
        this.taxa_administrativa = taxa_administrativa;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "FundoDeInvestimento{" +
                "nome='" + nome + '\'' +
                ", rentabilidade='" + rentabilidade + '\'' +
                ", liquidez='" + liquidez + '\'' +
                ", taxa_administrativa=" + taxa_administrativa +
                "} " + super.toString();
    }
}
