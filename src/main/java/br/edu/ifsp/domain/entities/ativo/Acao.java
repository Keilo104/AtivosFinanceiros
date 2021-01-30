package br.edu.ifsp.domain.entities.ativo;

import br.edu.ifsp.application.main.APIDAO;

import java.time.LocalDate;

public class Acao extends Ativo {
    private String codigo;
    private String pais;

    public Acao() {
    }

    public Acao(float valor, int quantidade, String codigo, String pais) {
        super(valor, quantidade);
        this.codigo = codigo;
        this.pais = pais;
    }

    public Acao(int id, float valorUnitarioAtual, float valorUnitarioComprado, float valorTotalVendido, LocalDate dataComprado, int quantidade, String codigo, String pais) {
        super(id, valorUnitarioAtual, valorUnitarioComprado, valorTotalVendido, dataComprado, quantidade);
        this.codigo = codigo;
        this.pais = pais;
    }

    public Acao(String codigo, String pais) {
        this.codigo = codigo;
        this.pais = pais;
    }

    public void updateFromAPI() {
        APIDAO apidao = new APIDAO();
        float newPrice = apidao.getNewPrice(codigo);
        if (newPrice > -1) {
            this.setValorUnitarioAtual(newPrice);
        } else {
            throw new InvalidPriceToUpdateException("Cannot update price");
        }
        notifyObservers();
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Acao{" +
                "pais='" + pais + '\'' +
                ", categoria='" + codigo + '\'' +
                "} " + super.toString();
    }
}
