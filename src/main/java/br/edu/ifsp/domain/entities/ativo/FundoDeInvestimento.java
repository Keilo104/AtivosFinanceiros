package br.edu.ifsp.domain.entities.ativo;

import java.time.LocalDate;

public class FundoDeInvestimento extends Ativo{
    private String rentabilidade;
    private String liquidez;

    public FundoDeInvestimento() {
    }

    public FundoDeInvestimento(float valor, int quantidade, String rentabilidade, String liquidez) {
        super(valor, quantidade);
        this.rentabilidade = rentabilidade;
        this.liquidez = liquidez;
    }

    public FundoDeInvestimento(int id, float valorAtual, float valorComprado, LocalDate data, int quantidade, String rentabilidade, String liquidez) {
        super(id, valorAtual, valorComprado, data, quantidade);
        this.rentabilidade = rentabilidade;
        this.liquidez = liquidez;
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

    @Override
    public String toString() {
        return "FundoDeInvestimento{" +
                "rentabilidade='" + rentabilidade + '\'' +
                ", liquidez='" + liquidez + '\'' +
                "} " + super.toString();
    }
}
