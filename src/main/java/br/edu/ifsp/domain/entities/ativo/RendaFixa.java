package br.edu.ifsp.domain.entities.ativo;

import java.time.LocalDate;

public class RendaFixa extends Ativo{
    private String tributacao;
    private String garantia;

    public RendaFixa() {
    }

    public RendaFixa(float valor, int quantidade, String tributacao, String garantia) {
        super(valor, quantidade);
        this.tributacao = tributacao;
        this.garantia = garantia;
    }

    public RendaFixa(int id, float valorAtual, float valorComprado, LocalDate data, int quantidade, String tributacao, String garantia) {
        super(id, valorAtual, valorComprado, data, quantidade);
        this.tributacao = tributacao;
        this.garantia = garantia;
    }

    public String getTributacao() {
        return tributacao;
    }

    public void setTributacao(String tributacao) {
        this.tributacao = tributacao;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    @Override
    public String toString() {
        return "RendaFixa{" +
                "tributacao='" + tributacao + '\'' +
                ", garantia='" + garantia + '\'' +
                "} " + super.toString();
    }
}
