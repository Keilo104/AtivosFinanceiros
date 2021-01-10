package br.edu.ifsp.domain.entities.ativo;

import br.edu.ifsp.domain.usecases.utils.Subject;

import java.time.LocalDate;
import java.util.Objects;

public class Ativo extends Subject {
    private int id;

    private float valorAtual;
    private float valorComprado;

    private LocalDate data;
    private int quantidade;

    public Ativo() {
        this.data = LocalDate.now();
    }

    public Ativo(float valor, int quantidade) {
        this(0, valor, valor, LocalDate.now(), quantidade);
    }

    public Ativo(int id, float valorAtual, float valorComprado, LocalDate data, int quantidade) {
        this.id = id;
        this.valorAtual = valorAtual;
        this.valorComprado = valorComprado;
        this.data = data;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValorTotalAtual() {
        return this.valorAtual * this.quantidade;
    }

    public float getValorTotalComprado() {
        return this.valorComprado * this.quantidade;
    }

    public float getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(float valorAtual) {
        this.valorAtual = valorAtual;
        notifyObservers();
    }

    public float getValorComprado() {
        return valorComprado;
    }

    public LocalDate getData() {
        return data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Ativo{" +
                "id=" + id +
                ", valorAtual=" + valorAtual +
                ", valorComprado=" + valorComprado +
                ", data=" + data +
                ", quantidade=" + quantidade +
                "} " + super.toString();
    }
}
