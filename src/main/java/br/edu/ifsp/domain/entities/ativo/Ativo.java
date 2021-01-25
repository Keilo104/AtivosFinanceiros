package br.edu.ifsp.domain.entities.ativo;

import br.edu.ifsp.domain.usecases.utils.Subject;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Ativo extends Subject {
    private int id;

    private float valorUnitarioAtual;
    private float valorUnitarioComprado;
    private float valorTotalVendido;

    private LocalDate dataComprado;
    private int quantidade;

    public Ativo() {
        this.dataComprado = LocalDate.now();
    }

    public Ativo(float valor, int quantidade) {
        this(0, valor, valor, 0, LocalDate.now(), quantidade);
    }

    public Ativo(int id, float valorUnitarioAtual, float valorUnitarioComprado, float valorTotalVendido, LocalDate dataComprado, int quantidade) {
        this.id = id;
        this.valorUnitarioAtual = valorUnitarioAtual;
        this.valorUnitarioComprado = valorUnitarioComprado;
        this.valorTotalVendido = valorTotalVendido;
        this.dataComprado = dataComprado;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValorTotalAtual() {
        return this.valorUnitarioAtual * this.quantidade;
    }

    public float getValorTotalComprado() {
        return this.valorUnitarioComprado * this.quantidade;
    }

    public float getValorUnitarioComprado() {
        return this.valorUnitarioComprado;
    }

    public float getValorTotalVendido() {
        return this.valorTotalVendido;
    }

    public float getValorUnitarioAtual() {
        return valorUnitarioAtual;
    }

    public void setValorUnitarioAtual(float valorUnitarioAtual) {
        this.valorUnitarioAtual = valorUnitarioAtual;
        notifyObservers();
    }

    public LocalDate getDataComprado() {
        return dataComprado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void vender(int quantidade) {
        if(quantidade > 0 && quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
            notifyObservers();
        } else {
            if(quantidade < 0) {
                throw new InvalidQuantityToVenderException("Cannot vender 0 or less ativos");
            } else {
                throw new InvalidQuantityToVenderException("Cannot vender more ativos than you have");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ativo ativo = (Ativo) o;
        return id == ativo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ativo{" +
                "id=" + id +
                ", valorUnitarioAtual=" + valorUnitarioAtual +
                ", valorUnitarioComprado=" + valorUnitarioComprado +
                ", valorTotalVendido=" + valorTotalVendido +
                ", dataComprado=" + dataComprado +
                ", quantidade=" + quantidade +
                "} " + super.toString();
    }
}
