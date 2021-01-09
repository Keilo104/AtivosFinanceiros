package br.edu.ifsp.domain.entities.ativo;

import java.time.LocalDate;

public class Acao extends Ativo {
    private String pais;
    private String categoria;

    public Acao() {
    }

    public Acao(float valor, int quantidade, String pais, String categoria) {
        super(valor, quantidade);
        this.pais = pais;
        this.categoria = categoria;
    }

    public Acao(int id, float valor, LocalDate data, int quantidade, String pais, String categoria) {
        super(id, valor, data, quantidade);
        this.pais = pais;
        this.categoria = categoria;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Acao{" +
                "pais='" + pais + '\'' +
                ", categoria='" + categoria + '\'' +
                "} " + super.toString();
    }
}
