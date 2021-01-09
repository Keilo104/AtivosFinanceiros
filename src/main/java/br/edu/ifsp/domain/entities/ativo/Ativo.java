package br.edu.ifsp.domain.entities.ativo;

import java.time.LocalDate;

public class Ativo {
    private int id;
    private float valor;
    private LocalDate data;
    private int quantidade;

    public Ativo() {
        this.data = LocalDate.now();
    }

    public Ativo(float valor, int quantidade) {
        this(0, valor, LocalDate.now(), quantidade);
    }

    public Ativo(int id, float valor, LocalDate data, int quantidade) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Ativo{" +
                "id=" + id +
                ", valor=" + valor +
                ", data=" + data +
                ", quantidade=" + quantidade +
                '}';
    }
}
